package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dao.JPAEmployee;
import ru.model.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private JPAEmployee jpaDao;

    @Transactional
    public void create(Employee employee) {
        jpaDao.save(employee);
    }

    @Transactional
    public void delete(int id) {
        jpaDao.delete(jpaDao.findById(id).get());
    }

    @Transactional
    public void update(Employee employee) {
        jpaDao.save(employee);
    }

    public Employee getById(int id) {
        return jpaDao.findById(id).get();
    }

    public List<Employee> getAll() {
        return jpaDao.findAll();
    }
}
