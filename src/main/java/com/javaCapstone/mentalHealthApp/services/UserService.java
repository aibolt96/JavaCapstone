package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.userDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(userDto userDto);

    List<String> userLogin(userDto userDto);
}
