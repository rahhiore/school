package ru.hogwarts.school.service;

import jdk.dynalink.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public  FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty get(Long id) {
        logger.info("Was invoked method for get faculty");
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return faculty.get();
        } else {
            logger.error("There is not faculty with id = " + id);
            throw new EntityNotFoundException();
        }
    }
    public Faculty post(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }
    public Faculty put(Long id, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        Faculty faculty2 = get(id);
        faculty2.setName(faculty.getName());
        faculty2.setColor(faculty.getColor());
        return facultyRepository.save(faculty2);
    }
    public Faculty delete(Long id) {
        logger.info("Was invoked method for get delete");
        Faculty faculty = get(id);
        facultyRepository.deleteById(id);
        return faculty;
    }
    public Collection<Faculty> findByColorIgnoreCaseOrByNameContainsIgnoreCase(String nameOrColor) {
        logger.info("Was invoked method for get faculties by name or color");
        return facultyRepository.findByColorIgnoreCaseOrNameContainsIgnoreCase(nameOrColor, nameOrColor);
    }

    public String getTheLongestName() {
        logger.info("Was invoked method for get the longest name");
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList.stream().map(Faculty::getName).max(Comparator.comparing(String::length)).get();
    }
    public Integer returnNum() {
        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a +1) .limit(1_000_000) .reduce(0, (a, b) -> a + b);
        long end = System.currentTimeMillis();
        logger.info("Time: " + (end - start));
        return sum;
    }
    public Integer returnNumWithParallel(Integer limit) {
        long start = System.currentTimeMillis();
        Integer sum = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(limit)
                .reduce(0, (a, b) -> a + b);
        long end = System.currentTimeMillis();
        logger.info("Time: " + (end - start));
        return sum;
    }
    public Integer returnNum(Integer limit) {
        long start = System.currentTimeMillis();
        Integer sum = Stream.iterate(1, a -> a +1) .limit(limit) .reduce(0, (a, b) -> a + b);
        long end = System.currentTimeMillis();
        logger.info("Time: " + (end - start));
        return sum;
    }
    public Integer returnNumWithIntStream(Integer limit) {
        long start = System.currentTimeMillis();
        Integer sum = IntStream.range(1, limit).sum();
        long end = System.currentTimeMillis();
        logger.info("Time: " + (end - start));
        return sum;
    }
}
