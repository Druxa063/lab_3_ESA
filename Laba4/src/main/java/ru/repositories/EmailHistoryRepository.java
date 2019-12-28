package ru.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.entities.EmailHistory;

import java.util.UUID;

public interface EmailHistoryRepository extends CrudRepository<EmailHistory, UUID> {

}
