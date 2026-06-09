package com.example.user_service.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    public User() {
    }

    public User(Integer id, String name, String email,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

public String getRole(){
        return role;
}
public void setRole(String role){
        this.role=role;
}

}