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
