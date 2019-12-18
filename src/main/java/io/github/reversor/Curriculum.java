package io.github.reversor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

public enum Curriculum {
  JAVA_EE("Java EE developer", Map.of(
      "Java SE", 40,
      "Servlets", 16,
      "JAX-WS", 16,
      "JAX-RS", 8,
      "CDI", 8
  )),
  JAVA_DEVELOPER("Java developer", Map.of(
      "Java SE", 40,
      "JDBC", 16,
      "JAX", 8
  ));

  private String name;
  private Map<String, Integer> coursesDuration;
  private Integer trainingDays;

  Curriculum(String name, Map<String, Integer> coursesDuration) {
    this.name = name;
    this.coursesDuration = coursesDuration;
    this.trainingDays = 0;
    for (Integer duration : coursesDuration.values()) {
      trainingDays += duration;
    }
  }

  @JsonCreator
  public static Curriculum forName(String name) {
    for (Curriculum curriculum : values()) {
      if (curriculum.name.equals(name)) {
        return curriculum;
      }
    }

    throw new IllegalArgumentException("Curriculum not found for name: " + name);
  }

  @JsonValue
  public String getName() {
    return name;
  }

  public Map<String, Integer> getCoursesDuration() {
    return coursesDuration;
  }

  public Integer getCourseDuration(String courseName) {
    return coursesDuration.get(courseName);
  }

  public Integer getDuration() {
    return trainingDays;
  }
}
