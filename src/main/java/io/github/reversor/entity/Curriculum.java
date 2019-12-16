package io.github.reversor.entity;

import java.util.Map;

public class Curriculum {

    private String name;
    private Map<String, Integer> courses;

    private Curriculum() {
    }

    public Curriculum create(String name, Map<String, Integer> courses) {
        Curriculum curriculum = new Curriculum();
        curriculum.name = name;
        curriculum.courses = courses;

        return curriculum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Integer> courses) {
        this.courses = courses;
    }
}
