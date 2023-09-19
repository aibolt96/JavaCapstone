package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.repositories.EmotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionsServiceImpl {
    @Autowired
    private EmotionsRepository emotionsRepository;
}
