package com.example.bugtter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bugtter.exception.NotFoundStatusException;
import com.example.bugtter.model.User;
import com.example.bugtter.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User verifyUser(Long id) {
		Optional<User> maybeUser = userRepository.findById(id);
		return maybeUser.orElseThrow(NotFoundStatusException::new);
	}

}
