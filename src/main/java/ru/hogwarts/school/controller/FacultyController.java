package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    @Autowired
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(faculty);
        }
    }
    @PostMapping
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.post(faculty);
        return ResponseEntity.ok(createFaculty);
    }
    @PutMapping()
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty putFaculty = facultyService.put(faculty.getId(), faculty);
        if (putFaculty == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(putFaculty);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> delete (@PathVariable Long id) {
        Faculty faculty = facultyService.delete(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(faculty);
        }
    }
    @GetMapping("color")
    public ResponseEntity<Collection<Faculty>> colorFilter(@RequestParam String color) {
        Collection<Faculty> faculty = facultyService.colorFilter(color);
        return ResponseEntity.ok(faculty);
    }
}
