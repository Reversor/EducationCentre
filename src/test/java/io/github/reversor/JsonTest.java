package io.github.reversor;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.Test;

public class JsonTest {

  @Test
  void readJson() {
    ObjectReader objectMapper = new ObjectMapper()
        .configure(WRITE_DATES_AS_TIMESTAMPS, false)
        .findAndRegisterModules().reader();

    Student[] o = assertDoesNotThrow(() -> objectMapper.forType(Student[].class)
        .readValue(getClass().getClassLoader().getResource("students.json"))
    );

    assertEquals(3, o.length);
  }

}
