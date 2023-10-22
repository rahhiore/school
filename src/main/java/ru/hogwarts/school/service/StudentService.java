package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student get(Long id) {
        logger.info("Was invoked method for get student");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            logger.error("There is not student with id = " + id);
            throw new EntityNotFoundException();
        }
    }
    public Student post(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }
    public Student put(Long id, Student student) {
        logger.info("Was invoked method for update student");
        Student student2 = get(id);
        student2.setName(student.getName());
        student2.setAge(student.getAge());
        return studentRepository.save(student2);
    }
    public Student delete(Long id) {
        logger.info("Was invoked method for delete student");
        Student student = get(id);
        studentRepository.deleteById(id);
        return student;
    }
    public Collection<Student> ageFilter(Integer age) {
        logger.info("Was invoked method for get students by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for get students by age range");
        return studentRepository.findByAgeBetween(min, max);
    }
    public Collection<Student> findByNamePartIgnoreCase(String name) {
        logger.info("Was invoked method for get students by part name with ignore case");
        return studentRepository.findByNameContainsIgnoreCase(name);
    }
    public Integer countStudent() {
        logger.info("Was invoked method for count students");
        return studentRepository.countStudent();
    }
    public Integer averageAge() {
        logger.info("Was invoked method for get average age");
        return studentRepository.averageAge();
    }
    public Collection<Student> lastFiveStudent() {
        logger.info("Was invoked method for get last five students");
        return studentRepository.lastFiveStudents();
    }

    public Collection<Student> getSortedStudentWithStartCharA() {
        logger.info("Was invoked method for get sorted students with start char A");
        List<Student> studentsList = studentRepository.findAll();
        return studentsList.stream().filter(student -> student.getName()
                .startsWith("A")).sorted().collect(Collectors.toList());
    }
    public OptionalDouble getAverageAgeWithStream() {
        logger.info("Was invoked method for get average age");
        List<Student> studentsList = studentRepository.findAll();
        return studentsList.stream().mapToInt(Student::getAge).average();
    }
}
