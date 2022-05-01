package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    void findById(Integer id);
    void findAll(List<Department> findAll);
    
}