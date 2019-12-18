package io.github.reversor;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static final Options OPTIONS = new Options()
      .addOption(Option.builder("S")
          .longOpt("students")
          .desc("Path to JSON file")
          .hasArg()
          .required().build())
      .addOption(Option.builder("s")
          .longOpt("sorted")
          .desc("Shows a students sorted by GPA or remaining days")
          .hasArg()
          .argName("Sorting parameter: GPA or remain")
          .build())
      .addOption(Option.builder("d")
          .desc("Students with a probability of deductions")
          .build());

  public static void main(String[] args) {
    try {
      CommandLine parse = new DefaultParser().parse(OPTIONS, args);

      Student[] students = readStudentsFromJson(Paths.get(parse.getOptionValue("S")).toFile());

      Stream<Student> studentStream = Arrays.stream(students);
      if (parse.hasOption('s')) {
        String s = parse.getOptionValue('s');
        if (s.equals("GPA")) {
          studentStream = studentStream
              .sorted(Comparator.comparingDouble(Student::getGPA));
        } else if (s.equals("remain")) {
          studentStream = studentStream
              .sorted(Comparator.comparingDouble(Student::getRemainingHours));
        }
      }

      if (parse.hasOption('d')) {
        //TODO упростить
        studentStream = studentStream.filter(s -> {
          if (s.getGPA() < 4.5 && s.getRemainingHours() > 0) {
            int neededMarksCount = (int) Math.floor((double) s.getCurriculum().getDuration() / 8);
            double minMarksSum = neededMarksCount * 4.5;

            double marksSum = 0;
            for (int mark : s.getMarks()) {
              marksSum += mark;
            }

            double diffMarksSum = marksSum - minMarksSum;
            double exceptedDiffMarksSum = (neededMarksCount - s.getMarks().length) * 5;

            return diffMarksSum < exceptedDiffMarksSum;
          }

          return s.getGPA() >= 4.5 && s.getRemainingHours() == 0;
        });
      }

      studentStream.forEach(student -> {
        Curriculum curriculum = student.getCurriculum();

        StringBuilder msg = new StringBuilder(student.getName())
            .append(" - до окончания обучения по программе ")
            .append(curriculum.getName())
            .append(" осталось ")
            .append(student.getRemainingHours())
            .append(" ч. Средний балл ")
            .append(String.format("%.2f", student.getGPA()))
            .append("\n");

        if (student.getRemainingHours() <= 0 && student.getGPA() < 4.5) {
          msg.append("На отчисление.");
        } else {
          msg.append("Может продолжать обучение.");
        }

        LOGGER.info(msg.toString());
      });

    } catch (ParseException e) {
      LOGGER.error("Wrong the application arguments", e);
    }
  }

  public static Student[] readStudentsFromJson(File file) {
    try {
      ObjectReader objectMapper = new ObjectMapper()
          .configure(WRITE_DATES_AS_TIMESTAMPS, false)
          .findAndRegisterModules().reader();

      return objectMapper.forType(Student[].class).readValue(file);
    } catch (IOException e) {
      LOGGER.error("Exception for reading json file with students");

      System.exit(1);
    }

    return new Student[0];
  }
}
