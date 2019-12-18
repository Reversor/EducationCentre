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

public class Application {

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

      if (parse.hasOption('s')) {
        Stream<Student> studentStream = Arrays.stream(students);

        switch (parse.getOptionValue('s')) {
          case "GPA":
            studentStream = studentStream
                .sorted(Comparator.comparingDouble(Student::getGPA));
            break;
          case "remain":
            studentStream = studentStream
                .sorted(Comparator.comparingDouble(Student::getRemainingHours));
            break;
        }

        if (parse.hasOption('d')) {
          //TODO
          // studentStream = studentStream.filter()
        }

        studentStream.forEach(student -> {
          Curriculum curriculum = student.getCurriculum();

          System.out.printf("%s - до окончания обучения по программе %s осталось %d ч. "
                  + "Средний балл %.1f.\n",
              student.getName(), curriculum.getName(),
              student.getRemainingHours(), student.getGPA()
          );

          if (student.getRemainingHours() <= 0 && student.getGPA() < 4.5) {
            System.out.println("На отчисление.");
          } else {
            System.out.println("Может продолжать обучение.");
          }
        });
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public static Student[] readStudentsFromJson(File file) {
    try {
      ObjectReader objectMapper = new ObjectMapper()
          .configure(WRITE_DATES_AS_TIMESTAMPS, false)
          .findAndRegisterModules().reader();

      return objectMapper.forType(Student[].class).readValue(file);
    } catch (IOException e) {
      e.printStackTrace();

      System.exit(1);
    }

    return new Student[0];
  }
}
