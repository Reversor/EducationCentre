package io.github.reversor;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
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
        printStudentsOnDemand(students);
      }
      if (parse.hasOption('s')) {
        switch (parse.getOptionValue('s')) {
          case "GPA":
            Arrays.stream(students)
                .sorted(Comparator.comparingDouble(Student::getGPA))
                .forEach(System.out::println);
            break;
          case "remain":
            Arrays.stream(students)
                .sorted(Comparator.comparingDouble(Student::getRemainingDays))
                .forEach(System.out::println);
            break;
          default:
            System.out.println("Unknown 'sort by' parameter");
        }

      }
      if (parse.hasOption('d')) {
        //TODO
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private static void printStudentsOnDemand(Student[] students) {
    System.out.println("Students on demand:");
    for (Student student : students) {
      if (student.getRemainingDays() <= 0 && student.getGPA() < 4.5) {
        System.out.println(student.getName());
      }
    }
  }

  private static void printStudentsGPA(Student[] students) {
    System.out.println("Students GPA:");
    for (Student student : students) {
      System.out.printf("%s: %.2f\n", student.getName(), student.getGPA());
    }
  }

  private static void printRemainingDays(Student[] students) {
    System.out.println("Remaining days for students:");
    for (Student student : students) {
      System.out.printf("%s: %.2f\n", student.getName(), student.getRemainingDays());
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
