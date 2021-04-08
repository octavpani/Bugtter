package com.example.bugtter.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bugtter.form.ReportForm;
import com.example.bugtter.model.CustomUserDetails;
import com.example.bugtter.model.Report;
import com.example.bugtter.repository.ReportRepository;
import com.example.bugtter.service.ReportService;
import com.example.bugtter.service.StatusService;
import com.example.bugtter.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
	private final ReportRepository repo;
	private final ReportService reportService;
	private final UserService userService;
	private final StatusService statusService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Report> repos = repo.findAll();
		model.addAttribute("repos", repos);
		return "report/index";
	}

	@GetMapping("/{id:^[\\d]+$}")
	public String show(@PathVariable("id") Long id, Model model) {
		ReportForm reportForm = new ReportForm(reportService.verifyReport(id));
		model.addAttribute("reportForm", reportForm);
		return "report/form";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Report report, ReportForm reportForm, Authentication loginUser, Model model) {
		CustomUserDetails ud = (CustomUserDetails) loginUser.getPrincipal();
		//ここは一つにまとめるべき？
		reportForm.setStatusService(statusService);
		report = reportForm.toEntity();
		report.setUser(ud.getUser());
		//report.setStatus(statusService.verifyStatus(reportForm.getStatus()));
		reportService.save(report);

		return "redirect:/reports/index";
	}

}
