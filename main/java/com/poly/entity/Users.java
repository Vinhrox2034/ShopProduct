package com.poly.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class Users implements Serializable {

    @Id
    @Column(name = "userId", length = 20)
    private String userId;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "role", length = 10)
    private String role; // USER | ADMIN

    @Column(name = "active")
    private Boolean active;

    // ===== Constructor =====
    public Users() {
    }

    // ===== Getter & Setter =====
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
