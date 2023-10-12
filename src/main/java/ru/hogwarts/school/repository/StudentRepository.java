package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findByNameContainsIgnoreCase(String name);
    Collection<Student> findByAge(Integer age);
    @Query(value = "SELECT count(id) as count_student FROM student", nativeQuery = true)
    Integer countStudent();
    @Query(value = "SELECT avg(age) as average_age FROM student", nativeQuery = true)
    Integer averageAge();
    @Query(value = "SELECT * FROM student ORDER BY id desc limit 5", nativeQuery = true)
    Collection<Student> lastFiveStudents();

}
