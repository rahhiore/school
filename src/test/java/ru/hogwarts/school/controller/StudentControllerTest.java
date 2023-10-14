package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.hogwarts.school.controller.TestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "classpath:application.properties")
class StudentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @LocalServerPort
    private int port;
    @Test
    void get() throws Exception{
        ResponseEntity<Student> newStudentRs =
                this.testRestTemplate.postForEntity("http://localhost:" + port + "/student", MOCK_STUDENT, Student.class);
         assertEquals(newStudentRs.getStatusCode(), HttpStatus.OK);
        Student newStudent = newStudentRs.getBody();

        ResponseEntity<Student> getStudentRs =
                testRestTemplate.getForEntity("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);
        assertEquals(getStudentRs.getStatusCode(), HttpStatus.OK);
        Student student = getStudentRs.getBody();
        assertEquals(student.getId(), newStudent.getId());
        assertEquals(student.getName(), newStudent.getName());
        assertEquals(student.getAge(), newStudent.getAge());
    }

    @Test
    void create() {

        ResponseEntity<Student> newStudentRs =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student", MOCK_STUDENT, Student.class);

        assertEquals(newStudentRs.getStatusCode(), HttpStatus.OK);
        Student newStudent = newStudentRs.getBody();

        assertEquals(newStudent.getName(), MOCK_STUDENT_NAME);
        assertEquals(newStudent.getAge(), MOCK_STUDENT_AGE);

    }

    @Test
    void put() {
        ResponseEntity<Student> newStudentRs =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student", MOCK_STUDENT, Student.class);

        testRestTemplate.put("http://localhost:" + port + "/student", MOCK_ANOTHER_STUDENT, Student.class);

        ResponseEntity<Student> getStudentRs =
                testRestTemplate.getForEntity("http://localhost:" + port + "/student/" + MOCK_STUDENT_ID, Student.class);

        assertEquals(getStudentRs.getStatusCode(), HttpStatus.OK);
        Student student = getStudentRs.getBody();
        assertEquals(student.getId(), MOCK_STUDENT_ID);
        assertEquals(student.getName(), MOCK_ANOTHER_STUDENT_NAME);
        assertEquals(student.getAge(), MOCK_STUDENT_AGE);
    }

    @Test
    void delete() {
        ResponseEntity<Student> newStudentRs =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student/", MOCK_STUDENT, Student.class);

        assertEquals(newStudentRs.getStatusCode(), HttpStatus.OK);
        Student newStudent = newStudentRs.getBody();
        testRestTemplate.delete("http://localhost:" + port + "/student" + newStudent.getId(), Student.class);

        ResponseEntity<Student> getStudentRs =
                testRestTemplate.getForEntity("http://localhost:" + port +
                        "/student" + newStudent.getId(), Student.class);
        Student student = getStudentRs.getBody();
        assertThat(student.getId()).isNull();
    }


    @Test
    void findByAgeBetween() {
        assertNotNull(testRestTemplate.getForEntity("http://localhost:"  + port +
                "/student?min=5&max=10", String.class));
    }

    @Test
    void getStudentByName() {
        assertNotNull(testRestTemplate.getForEntity("http://localhost:"  + port +
                "/student/name?namePart=" + MOCK_STUDENT_AGE, String.class));
    }
}