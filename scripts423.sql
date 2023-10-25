SELECT s.name, s.age, f.name
FROM student s
JOIN faculties f ON s.faculty_id = f.id;

SELECT s.name, s.age, f.name
FROM student s
JOIN faculties f ON s.faculty_id = f.id
JOIN avatar a ON s.id = a.student_id