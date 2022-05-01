package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    
    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Integer id);
    void findById(Integer id);
    void findAll(List<Seller> findAll);
    
}