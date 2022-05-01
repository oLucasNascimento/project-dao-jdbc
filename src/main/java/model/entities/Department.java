package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
    
    private Integer id;
    private String name;
    
    public Department() {
    }
    
    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(this.getId(), that.getId());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                '}';
    }
}