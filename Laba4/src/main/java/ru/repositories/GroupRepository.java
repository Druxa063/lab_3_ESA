package ru.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.entities.Group;

import java.util.List;
import java.util.UUID;

public interface GroupRepository extends CrudRepository<Group, UUID> {
    List<Group> findAll();

}
