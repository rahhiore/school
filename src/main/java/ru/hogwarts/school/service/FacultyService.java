package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Long counter = 0L;
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    public Faculty get(Long id) {
        return facultyMap.get(id);
    }
    public Faculty put(Faculty faculty) {
        facultyMap.put(counter, faculty);
        counter++;
        return faculty;
    }
    public Faculty update(Long id, Faculty faculty) {
        facultyMap.put(counter, faculty);
        return faculty;
    }
    public Faculty delete(Long id) {
        return facultyMap.remove(counter);
    }
    public Collection<Faculty> colorFilter(String color) {
        return facultyMap.values().stream().filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
