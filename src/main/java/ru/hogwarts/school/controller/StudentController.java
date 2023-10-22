package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.OptionalDouble;

@RequestMapping(value = "/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping(value = "{id}")
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
    @GetMapping("ageBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        Collection<Student> student = studentService.findByAgeBetween(min, max);
        return ResponseEntity.ok(student);
    }
    @GetMapping("name")
    public ResponseEntity<Collection<Student>> getStudentByName(String namePart) {
        return ResponseEntity.ok(studentService.findByNamePartIgnoreCase(namePart));
    }

    @GetMapping("{id}/faculty")
     public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.get(id).getFaculty());
    }
    @GetMapping("count")
    public ResponseEntity<Integer> getCountStudent() {
        return ResponseEntity.ok(studentService.countStudent());
    }
    @GetMapping("average_age")
    public ResponseEntity<Integer> getAverageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }
    @GetMapping(value = "last_five_student")
    public ResponseEntity<Collection<Student>> getLastFiveStudent() {
        Collection<Student> student = studentService.lastFiveStudent();
        return ResponseEntity.ok(student);
    }
    @GetMapping("sorted_students")
    public ResponseEntity<Collection<Student>> getSortedStudents() {
        return ResponseEntity.ok(studentService.getSortedStudentWithStartCharA());
    }
    @GetMapping("average_age_stream")
    public ResponseEntity<OptionalDouble> getAverageAgeStream() {
        return ResponseEntity.ok(studentService.getAverageAgeWithStream());
    }
}
