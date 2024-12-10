package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    private final DateTimeParser parser = new HabrCareerDateTimeParser();

    @Test
    void whenValidDateTimeThenParseCorrectly() {
        String input = "2024-12-05T20:27:20";
        LocalDateTime expected = LocalDateTime.of(2024, 12, 5, 20, 27, 20);
        LocalDateTime actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @Test
    void whenInvalidDateTimeThenThrowException() {
        String invalidInput = "2024-12-05 20:27:20";
        assertThrows(java.time.format.DateTimeParseException.class, () -> parser.parse(invalidInput));
    }

    @Test
    void whenEmptyStringThenThrowException() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> parser.parse(""));
    }

    @Test
    void whenEdgeDateTimeThenParseCorrectly() {
        String edgeDate = "2024-01-01T00:00:00";
        LocalDateTime expected = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime actual = parser.parse(edgeDate);
        assertEquals(expected, actual);
    }

    @Test
    void whenDifferentDateTimeThenParseCorrectly() {
        String input = "2024-05-15T13:45:00";
        LocalDateTime expected = LocalDateTime.of(2024, 5, 15, 13, 45, 0);
        LocalDateTime actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @Test
    void whenValidDateTimeWithTimeZoneThenParseCorrectly() {
        String input = "2024-12-05T20:27:20+03:00";
        LocalDateTime expected = LocalDateTime.of(2024, 12, 5, 20, 27, 20);
        LocalDateTime actual = parser.parse(input);
        assertEquals(expected, actual);
    }
}