package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RequestMapping(value = "faculty")
@RestController

public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = facultyService.get(id);
        return ResponseEntity.ok(faculty);
    }
    @PostMapping
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.post(faculty);
        return ResponseEntity.ok(createFaculty);
    }
    @PutMapping()
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty putFaculty = facultyService.put(faculty.getId(), faculty);
        return ResponseEntity.ok(putFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> delete (@PathVariable Long id) {
        Faculty faculty = facultyService.delete(id);
        return ResponseEntity.ok(faculty);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultyByColorOrName(@RequestParam String nameOrColor) {
        return ResponseEntity.ok(facultyService.findByColorIgnoreCaseOrByNameContainsIgnoreCase(nameOrColor));

    }
    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<Student>> getStudentByID(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.get(id).getStudent());
    }
    @GetMapping("/longest_name")
    public ResponseEntity<String> getTheLongestName() {
        return ResponseEntity.ok(facultyService.getTheLongestName());
    }
    @GetMapping("/return_num")
    public ResponseEntity<String> returnNum(@RequestParam Integer limit) {
        return ResponseEntity.ok("Num without parallel: " + facultyService.returnNum(limit)
                + "\nNum with parallel: " + facultyService.returnNumWithParallel(limit)
                + "\nNum with integer stream: " + facultyService.returnNumWithIntStream(limit));
    }
}
