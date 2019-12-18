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
  private Integer remainingHours;
  private Double gpa;

  public boolean mayNotBeExpelled() {
    if (getGPA() < 4.5 && getRemainingHours() > 0) {
      int neededMarksCount = (int) Math.floor((double) curriculum.getDuration() / 8);

      double marksSum = 0;
      for (int mark : marks) {
        marksSum += mark;
      }

      double diffMarksSum = neededMarksCount * 4.5 - marksSum;
      double exceptedMinDiffMarksSum = (neededMarksCount - marks.length) * 5;

      return diffMarksSum < exceptedMinDiffMarksSum;
    }

    return getGPA() >= 4.5;
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
    if (remainingHours == null) {
      remainingHours = curriculum.getDuration() - marks.length * 8;
    }

    return remainingHours;
  }

  public String getName() {
    return name;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public int[] getMarks() {
    return marks;
  }

  public Curriculum getCurriculum() {
    return curriculum;
  }

}
