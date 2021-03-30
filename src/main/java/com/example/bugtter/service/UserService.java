package com.example.bugtter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bugtter.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

}
