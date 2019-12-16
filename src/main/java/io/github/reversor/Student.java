package io.github.reversor;

import java.util.HashMap;
import java.util.Map;

public class Student {

    private String name;
    private Curriculum curriculum;
    private Map<String, Integer> remainingHoursForCourses;

    private Student() {
    }

    public static Student fromCurriculum(String name, Curriculum curriculum) {
        Student student = new Student();
        student.name = name;
        student.curriculum = curriculum;
        student.remainingHoursForCourses = new HashMap<>(curriculum.getCoursesWithDuration());

        return student;
    }

    public static Student fromCoursesWithCurriculumName(String studentName, String curriculumName,
            Map<String, Integer> hoursSpentForCourses) {
        Student student = new Student();
        student.name = studentName;
        student.curriculum = Curriculum.forName(curriculumName);
        student.remainingHoursForCourses = hoursSpentForCourses;

        return student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getRemainingHoursForCourses() {
        return remainingHoursForCourses;
    }

    public void setRemainingHoursForCourses(Map<String, Integer> remainingHoursForCourses) {
        this.remainingHoursForCourses = remainingHoursForCourses;
    }

    public void setRemainingHoursForCourse(String courseName, Integer hours) {

    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
