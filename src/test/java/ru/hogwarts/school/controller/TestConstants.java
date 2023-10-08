package ru.hogwarts.school.controller;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestConstants {
    public static final Long MOCK_FACULTY_ID = 1L;
    public static final String MOCK_FACULTY_NAME = "Name";
    public static final String MOCK_FACULTY_ANOTHER_NAME = "Another_Name";
    public static final String MOCK_FACULTY_COLOR = "Color";

    public static final Faculty MOCK_FACULTY = new Faculty(MOCK_FACULTY_ID, MOCK_FACULTY_NAME, MOCK_FACULTY_COLOR);
    public static final Faculty MOCK_FACULTY_ANOTHER = new Faculty(MOCK_FACULTY_ID, MOCK_FACULTY_ANOTHER_NAME, MOCK_FACULTY_COLOR);
    public static final List<Faculty> MOCK_FACULTIES = Collections.singletonList(MOCK_FACULTY);

    public static final Long MOCK_STUDENT_ID = 1L;
    public static final String MOCK_STUDENT_NAME = "Name";
    public static final Integer MOCK_STUDENT_AGE = 6;
    public static final String MOCK_ANOTHER_STUDENT_NAME = "Another_name";
    public static final Student MOCK_STUDENT = new Student(
            MOCK_STUDENT_ID,
            MOCK_STUDENT_NAME,
            MOCK_STUDENT_AGE
    );
    public static final Student MOCK_ANOTHER_STUDENT = new Student(
            MOCK_STUDENT_ID,
            MOCK_ANOTHER_STUDENT_NAME,
            MOCK_STUDENT_AGE
    );
}
