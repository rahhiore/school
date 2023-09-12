package ru.hogwarts.school.service;

import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Long counter = 0L;
    private final Map<Long, Student> studentMap = new HashMap<>();
    public Student get(Long id) {
        return studentMap.get(id);
    }
    public Student post(Student student) {
        studentMap.put(counter, student);
        counter++;
        return student;
    }
    public Student put(Long id, Student student) {
        studentMap.put(counter, student);
        return student;
    }
    public Student delete(Long id) {
        return studentMap.remove(counter);
    }
    public Collection<Student> ageFilter(Integer age) {
        return studentMap.values().stream().filter(student -> student.getAge().equals(age))
                .collect(Collectors.toList());
    }
}
