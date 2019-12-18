package io.github.reversor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.time.LocalDate;

@JsonRootName("student")
public class Student {

  private String name;
  private Curriculum curriculum;
  @JsonProperty("start_date")
  private LocalDate startDate;
  private int[] marks;
  private Integer remainingDays;
  private Double gpa;

  public Student() {
  }

  public Student(String name, Curriculum curriculum, LocalDate startDate, int[] marks) {
    this.name = name;
    this.curriculum = curriculum;
    this.startDate = startDate;
    this.marks = marks;
  }

  public double getGPA() {
    if (gpa == null) {
      int marksSum = 0;
      for (int mark : marks) {
        marksSum += mark;
      }

      gpa = (double) marksSum / marks.length;
    }

    return gpa;
  }

  public int getRemainingHours() {
    if (remainingDays == null) {
      remainingDays = curriculum.getDuration() - marks.length * 8;
    }

    return remainingDays;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public int[] getMarks() {
    return marks;
  }

  public void setMarks(int[] marks) {
    this.marks = marks;
  }

  public Curriculum getCurriculum() {
    return curriculum;
  }

  public void setCurriculum(Curriculum curriculum) {
    this.curriculum = curriculum;
  }
}
