package io.github.reversor.entity;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Objects;

public class StudentDto {

    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String courseName;
    @CsvBindByPosition(position = 3)
    private Integer hours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentDto that = (StudentDto) o;
        return name.equals(that.name) &&
                courseName.equals(that.courseName) &&
                hours.equals(that.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseName, hours);
    }
}
