package io.github.reversor.entity;

import java.util.Map;

public class Student {

    private String name;
    private Map<String, Integer> hoursPerCourseId;

    private Student() {
    }

    public static Student create(String name, Map<String, Integer> courses) {
        Student student = new Student();
        student.name = name;
        student.hoursPerCourseId = courses;

        return student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getHoursPerCourseId() {
        return hoursPerCourseId;
    }

    public void setHoursPerCourseId(Map<String, Integer> hoursPerCourseId) {
        this.hoursPerCourseId = hoursPerCourseId;
    }
}
