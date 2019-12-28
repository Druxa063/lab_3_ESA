package ru.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.entities.History;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends CrudRepository<History, UUID> {
    List<History> findAll();
}
