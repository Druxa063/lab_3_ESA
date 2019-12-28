package ru.service;

import ru.model.Employee;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    void delete(int id);

    void update(Employee employee);

    Employee getById(int id);

    List<Employee> getAll();
}
