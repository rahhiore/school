-- liquibase formatted sql

--changeset Student:first
CREATE INDEX student_name_index ON student (name);

--changeset Student:second
CREATE INDEX faculty_color_and_name_index ON faculties (name, color);