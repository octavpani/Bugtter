package com.example.bugtter.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bugtter.model.Report;
import com.example.bugtter.repository.ReportRepository;
import com.example.bugtter.service.ReportService;
import com.example.bugtter.service.StatusService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
	private final ReportRepository repo;
	private final ReportService reportService;
	private final StatusService statusService;


	@GetMapping("/index")
	public String index(Model model) {
		List<Report>repos = repo.findAll();
		model.addAttribute("repos", repos);
		return "report/index";
	}

	@GetMapping("/{id:^[\\d]+$}")
	public String show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("report", reportService.verifyReport(id));
		return "report/form";
	}


}
