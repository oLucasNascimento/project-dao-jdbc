package model.dao.impl;

import com.mysql.cj.jdbc.exceptions.SQLError;
import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    
    private Connection connection;
    
    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void insert(Seller seller) {
    
    }
    
    @Override
    public void update(Seller seller) {
    
    }
    
    @Override
    public void deleteById(Integer id) {
    
    }
    
    @Override
    public Seller findById(Integer id) {
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            
            statement = connection.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                
                Department department = new Department();
                department.setId(resultSet.getInt("DepartmentId"));
                department.setName(resultSet.getString("DepName"));
                
                Seller seller = new Seller();
                seller.setId(resultSet.getInt("Id"));
                seller.setName(resultSet.getString("Name"));
                seller.setEmail(resultSet.getString("Email"));
                seller.setBaseSaraly(resultSet.getDouble("BaseSalary"));
                seller.setBirthDate(resultSet.getDate("BirthDate"));
                seller.setDepartment(department);
                
                return seller;
                
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatements(statement);
            DB.closeResultSet(resultSet);
        }
        
    }
    
    @Override
    public List<Seller> findAll(List<Seller> findAll) {
        return null;
    }
    
    @Override
    public List<Seller> findByDepartment(Department department) {
        return null;
    }
}