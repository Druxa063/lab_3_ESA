package ru.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.entities.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {
    List<Lesson> findAll();

    List<Lesson> findAllByGroupId(UUID id);

    List<Lesson> findAllByTeacherId(UUID id);

}
