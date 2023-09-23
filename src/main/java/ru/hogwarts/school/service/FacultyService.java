package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public  FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty get(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return faculty.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
    public Faculty post(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty put(Long id, Faculty faculty) {
        Faculty faculty2 = get(id);
        faculty2.setName(faculty.getName());
        faculty2.setColor(faculty.getColor());
        return facultyRepository.save(faculty2);
    }
    public Faculty delete(Long id) {
        Faculty faculty = get(id);
        facultyRepository.deleteById(id);
        return faculty;
    }
    public Collection<Faculty> colorFilter(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }
    public Collection<Faculty> findByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
    public Collection<Faculty> findByNamePartIgnoreCase(String name) {
        return facultyRepository.findByNameContainsIgnoreCase(name);
    }
    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}
