package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.entities.user;
import com.javaCapstone.mentalHealthApp.dto.userDto;
import com.javaCapstone.mentalHealthApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(userDto userDto){
        List<String> response = new ArrayList<>();
        user user = new user(userDto);
        userRepository.saveAndFlush(user);
        response.add("User Added Successfully");
        return response;
    }

    @Override
    public List<String> userLogin(userDto userDto){
        List<String> response = new ArrayList<>();
        Optional<user> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getUserId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }
}
