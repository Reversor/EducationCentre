package io.github.reversor.entity;

import java.time.Duration;

public class Course {

  private String name;
  private Duration duration;

  private Course() {
  }

  public static Course create(String name, Duration duration) {
    Course course = new Course();
    course.name = name;
    course.duration = duration;

    return course;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }
}
