package com.adcc.rest1demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Liu.DA on 2019/9/24
 */
@Entity
@Table(name = "t_user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;

    private String password;

    private String salt;

    private  String role;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getSalt() {
        return salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
