package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    
    private Connection connection;
    
    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void insert(Department department) {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, department.getName());


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(resultSet);

            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatements(statement);
        }

    }
    
    @Override
    public void update(Department department) {
    
        PreparedStatement statement = null;
    
        try {
            
            statement = connection.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            statement.executeUpdate();
        
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatements(statement);
        }
    
    }
    
    @Override
    public void deleteById(Integer id) {
        
        PreparedStatement statement = null;
    
        try {
        
            statement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
            statement.setInt(1, id);
        
            int rowsAffected = statement.executeUpdate();
        
            if (rowsAffected == 0) {
                throw new DbException("No department found with this ID");
            }
        
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatements(statement);
        }
    }
    
    @Override
    public Department findById(Integer id) {
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            
            statement = connection.prepareStatement("SELECT * FROM department WHERE Id = ?");
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return instantiateDepartment(resultSet);
            } else {
                throw new DbException("No department found with this ID");
            }
            
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatements(statement);
            DB.closeResultSet(resultSet);
        }
    }
    
    @Override
    public List<Department> findAll() {
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            
            statement = connection.prepareStatement("SELECT * FROM department ORDER BY Name");
            resultSet = statement.executeQuery();
            
            List<Department> list = new ArrayList<>();
            
            while (resultSet.next()) {
                
                Department department = instantiateDepartment(resultSet);
                list.add(department);
                
            }
            return list;
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatements(statement);
            DB.closeResultSet(resultSet);
        }
    }
    
    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("Name"));
        
        return department;
        
    }
}