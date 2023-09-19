package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
        Student student2 = get(id);
        student2.setName(student.getName());
        student2.setAge(student.getAge());
        return studentRepository.save(student2);
    }
    public Student delete(Long id) {
        Student student = get(id);
        studentRepository.deleteById(id);
        return student;
    }
    public Collection<Student> ageFilter(Integer age) {
        return studentRepository.findByAge(age);
    }
    public Collection<Student> findByName(String name) {
        return studentRepository.findByNameIgnoreCase(name);
    }
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
    public Collection<Student> findByNamePartIgnoreCase(String name) {
        return studentRepository.findByNamePartIgnoreCase(name);
    }
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
