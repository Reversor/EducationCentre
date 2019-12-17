package io.github.reversor;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
      .addOption(Option.builder("r")
          .longOpt("remainingDays")
          .desc("Calc remaining days for students")
          .build())
      .addOption(Option.builder("G")
          .longOpt("GPA")
          .desc("Calculate average marks")
          .build())
      .addOption(Option.builder("D")
          .longOpt("deduction")
          .desc("Shows a students on deduction")
          .build())
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

      if (parse.hasOption('r')) {
        printRemainingDays(students);
      }
      if (parse.hasOption('G')) {
        printStudentsGPA(students);
      }
      if (parse.hasOption('D')) {
        //TODO
      }
      if (parse.hasOption('s')) {
        //TODO
      }
      if (parse.hasOption('d')) {
        //TODO
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private static void printStudentsGPA(Student[] students) {
    System.out.println("Students GPA:");
    for (Student student : students) {
      int[] marks = student.getMarks();

      int marksSum = 0;
      for (int mark : marks) {
        marksSum += mark;
      }

      double gpa = (double) marksSum / marks.length;

      System.out.printf("%s: %.2f\n", student.getName(), gpa);
    }
  }

  private static void printRemainingDays(Student[] students) {
    System.out.println("Remaining days for students:");
    for (Student student : students) {
      double maxDays = 0;
      for (Integer duration : student.getCurriculum().getCoursesDuration().values()) {
        maxDays += (double) duration / 8;
      }

      int spentDays = student.getMarks().length;

      System.out.printf("%s: %.2f\n", student.getName(), maxDays - spentDays);
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
    }

    return null;
  }
}
