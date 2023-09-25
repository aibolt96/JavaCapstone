package com.javaCapstone.mentalHealthApp.dto;

import com.javaCapstone.mentalHealthApp.entities.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDto implements Serializable {
    private Long userId;
    private String username;
    private String password;
    private Set<entriesDto> entriesDtoSet = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public userDto() {
    }

    public userDto(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<entriesDto> getEntriesDtoSet() {
        return entriesDtoSet;
    }

    public void setEntriesDtoSet(Set<entriesDto> entriesDtoSet) {
        this.entriesDtoSet = entriesDtoSet;
    }

    public userDto(user user) {
        if (user.getUserId() != null){
            this.userId = user.getUserId();
        }
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getPassword() != null){
            this.password = user.getPassword();
        }
    }
}
