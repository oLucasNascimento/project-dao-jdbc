package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
    
    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSaraly;
    
    private Department department;
    
    public Seller() {
    }
    
    public Seller(Integer id, String name, String email, Date birthDate, Double baseSaraly, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSaraly = baseSaraly;
        this.department = department;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public Double getBaseSaraly() {
        return baseSaraly;
    }
    
    public void setBaseSaraly(Double baseSaraly) {
        this.baseSaraly = baseSaraly;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(this.getId(), seller.getId());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
    @Override
    public String toString() {
        return "Seller{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", birthDate=" + this.birthDate +
                ", baseSaraly=" + this.baseSaraly +
                ", department=" + this.department +
                '}';
    }
}