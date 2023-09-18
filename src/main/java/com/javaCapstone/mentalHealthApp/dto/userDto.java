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
