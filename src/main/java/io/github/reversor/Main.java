package io.github.reversor;

import io.github.reversor.entity.Course;
import io.github.reversor.entity.Student;
import java.time.Duration;
import java.util.Map;

public class Main {

  private final static Map<Integer, Course> COURSES = Map.of(
      1, Course.create("Java SE", Duration.ofHours(42)),
      2, Course.create("Spring", Duration.ofHours(42)),
      3, Course.create("JavaFX", Duration.ofHours(8)),
      4, Course.create("Hibernate", Duration.ofHours(16))
  );

  private final static Map<Integer, Student> STUDENTS = Map.of(
      1, Student.create(
          "Ivanov Ivan",
          Map.of(
              1, 30,
              2, 20
          )),
      2, Student.create(
          "Petrov Petr",
          Map.of(
              3, 8,
              4, 15
          )
      )
  );

  public static void main(String[] args) {
    // write your code here
  }
}
