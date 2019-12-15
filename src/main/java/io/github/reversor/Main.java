package io.github.reversor;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import io.github.reversor.entity.Course;
import io.github.reversor.entity.StudentDto;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class Main {

    public static final String STUDENTS_CSV = "./src/main/resources/students.csv";

    private static Set<Course> courses;

    public static void main(String[] args) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(STUDENTS_CSV));

            CsvToBean<StudentDto> csvToBean = new CsvToBean<>();
            csvToBean.setMappingStrategy(new ColumnPositionMappingStrategy<>());
            csvToBean.setCsvReader(new CSVReader(reader));
//          TODO csvToBean.stream().collect()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
