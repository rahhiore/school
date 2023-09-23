package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
        return ResponseEntity.ok(student);
    }
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student createStudent = studentService.post(student);
        return ResponseEntity.ok(createStudent);
    }
    @PutMapping()
    public ResponseEntity<Student> put(@RequestBody Student student) {
        Student putStudent = studentService.put(student.getId(), student);
        return ResponseEntity.ok(putStudent);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete (@PathVariable Long id) {
        Student student = studentService.delete(id);
        return ResponseEntity.ok(student);
    }
    @GetMapping("age")
    public ResponseEntity<Collection<Student>> ageFilter(@RequestParam Integer age) {
        Collection<Student> student = studentService.ageFilter(age);
        return ResponseEntity.ok(student);
    }
    @GetMapping("all")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        Collection<Student> student = studentService.findByAgeBetween(min, max);
        return ResponseEntity.ok(student);
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false) String namePart) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(studentService.findByName(name));
        }
        if (namePart != null && !namePart.isBlank()) {
            return ResponseEntity.ok(studentService.findByNamePartIgnoreCase(namePart));
        }
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("faculty/{id}")
     public ResponseEntity<Faculty> getFaculty(Long id) {
        return ResponseEntity.ok(studentService.get(id).getFaculty());
    }
}
