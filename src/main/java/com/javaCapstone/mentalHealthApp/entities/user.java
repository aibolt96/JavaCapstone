package com.javaCapstone.mentalHealthApp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javaCapstone.mentalHealthApp.dto.userDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;
    @Column
    private String password;

    public user(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public user() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser_id(Long userId) {
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<entries> entriesSet = new HashSet<>();

    public user(userDto userDto){
        if (userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }
}
