package ru.dao;

import org.springframework.data.repository.CrudRepository;
import ru.model.Employee;

import java.util.List;

public interface JPAEmployee extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();

}
