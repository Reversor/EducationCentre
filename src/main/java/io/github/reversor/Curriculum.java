package io.github.reversor;

import java.util.Map;

public enum Curriculum {
    JAVA_EE("Java EE developer", Map.of(
            "Servlets", 16,
            "JAX-RS", 8,
            "CDI", 8
    )),
    JAVA_DEVELOPER("Java developer", Map.of(
            "Java SE", 40,
            "JDBC", 16,
            "JAX", 8
    ));

    private String name;
    private Map<String, Integer> coursesWithDuration;

    Curriculum(String name, Map<String, Integer> coursesWithDuration) {
        this.name = name;
        this.coursesWithDuration = coursesWithDuration;
    }

    public static Curriculum forName(String name) {
        for (Curriculum curriculum : values()) {
            if (curriculum.name.equals(name)) {
                return curriculum;
            }
        }

        throw new IllegalArgumentException("Curriculum not found for name: " + name);
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getCoursesWithDuration() {
        return coursesWithDuration;
    }

    public Integer getCourseDuration(String courseName) {
        return coursesWithDuration.get(courseName);
    }
}
