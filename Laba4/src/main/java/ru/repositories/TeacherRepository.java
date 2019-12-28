package ru.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.entities.Teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
    List<Teacher> findAll();
}
