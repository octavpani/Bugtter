package com.example.bugtter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bugtter.exception.NotFoundStatusException;
import com.example.bugtter.model.Status;
import com.example.bugtter.repository.StatusRepository;

@Service
public class StatusService {
	@Autowired
	private StatusRepository statusRepository;

	public Status verifyStatus(Long id) {
		Optional<Status> maybeStatus = statusRepository.findById(id);
		return maybeStatus.orElseThrow(NotFoundStatusException::new);
	}

}
