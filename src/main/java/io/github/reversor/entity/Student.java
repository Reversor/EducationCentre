package io.github.reversor.entity;

import java.util.Map;

public class Student {

  private Integer id;
  private String name;
  private Map<Integer, Integer> hoursPerCourseId;

  private Student() {
  }

  public static Student create(String name, Map<Integer, Integer> courses) {
    Student student = new Student();
    student.name = name;
    student.hoursPerCourseId = courses;

    return student;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<Integer, Integer> getHoursPerCourseId() {
    return hoursPerCourseId;
  }

  public void setHoursPerCourseId(Map<Integer, Integer> hoursPerCourseId) {
    this.hoursPerCourseId = hoursPerCourseId;
  }
}
