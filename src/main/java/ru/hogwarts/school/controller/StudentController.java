package ru.hogwarts.school.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentService.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student createStudent = studentService.post(student);
        return ResponseEntity.ok(createStudent);
    }
    @PutMapping()
    public ResponseEntity<Student> put(@RequestBody Student student) {
        Student putStudent = studentService.put(student.getId(), student);
        if (putStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(putStudent);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete (@PathVariable Long id) {
        Student student = studentService.delete(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }
    @GetMapping("age")
    public ResponseEntity<Collection<Student>> ageFilter(@RequestParam Integer age) {
        Collection<Student> student = studentService.ageFilter(age);
        return ResponseEntity.ok(student);
    }
}
