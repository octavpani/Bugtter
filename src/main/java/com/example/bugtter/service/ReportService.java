package com.example.bugtter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bugtter.exception.NotFoundStatusException;
import com.example.bugtter.model.Report;
import com.example.bugtter.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;

	public Report verifyReport(Long id) {
		Optional<Report> maybeReport = reportRepository.findById(id);
		return maybeReport.orElseThrow(NotFoundStatusException::new);
	}

}
