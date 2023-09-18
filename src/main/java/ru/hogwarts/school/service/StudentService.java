package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student get(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
    public Student post(Student student) {
        return studentRepository.save(student);
    }
    public Student put(Long id, Student student) {
        return studentRepository.save(student);
    }
    public Student delete(Long id) {
        Student student = get(id);
        studentRepository.deleteById(id);
        return student;
    }
    public Collection<Student> ageFilter(Integer age) {
        return studentRepository.findAll().stream().filter(student -> student.getAge().equals(age))
                .collect(Collectors.toList());
    }
}
