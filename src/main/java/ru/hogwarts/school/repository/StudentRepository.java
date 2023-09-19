package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findByNameIgnoreCase(String name);
    Collection<Student> findByNamePartIgnoreCase(String name);
    Collection<Student> findByAge(Integer age);

}
