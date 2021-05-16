package com.example.bugtter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bugtter.form.ReportForm;
import com.example.bugtter.form.ReportQuery;
import com.example.bugtter.model.CustomUserDetails;
import com.example.bugtter.model.Report;
import com.example.bugtter.model.User;
import com.example.bugtter.service.ReportService;
import com.example.bugtter.util.Utils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
	//private final ReportRepository repo;
	private final ReportService reportService;
	@Autowired
	private final ConversionService conversionService;

	@GetMapping("/index")
	public String index(Authentication loginUser, ReportQuery repoQue,
			@PageableDefault(size=10)Pageable pageable,
			@RequestParam(name = "title", required=false) String title,
			@RequestParam(name = "urgency", required=false) Integer urgency,
			@RequestParam(name = "status_Id", required=false) Integer status_Id,
			Model model) {
		//リスト表示
		Page<Report> reports = reportService.findReports(pageable, repoQue);
		model.addAttribute("pathWithPage", Utils.pathWithPage("", pageable, "title", title, "urgency", urgency, "status_Id", status_Id));
		model.addAttribute("pathWithSort", Utils.pathWithSort("", pageable, "title", title, "urgency", urgency, "status_Id", status_Id));
		model.addAttribute("reportList", reports.getContent());
		model.addAttribute("reports", reports);
		return "report/index";
	}

	@GetMapping("/{id:^[\\d]+$}")
	public String show(@PathVariable("id") Long id, Model model) {
		ReportForm reportForm = new ReportForm(reportService.verifyReport(id));
		model.addAttribute("reportForm", reportForm);
		return "report/form";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Report report, @Validated ReportForm reportForm,BindingResult result,  Authentication loginUser, Model model) {
		CustomUserDetails ud = (CustomUserDetails) loginUser.getPrincipal();
		//validationにかかった時、値は、nullになる。
		//だから、jsの!条件にかかって、エラーメッセージに入る。
		if (result.hasErrors() ) {
			model.addAttribute("mode", "reCreate");
			return "report/form";
		}


		//Inject ConversionService on ReportForm
		reportForm.setConversionService(conversionService);
		report = reportForm.toEntity();
		report.setUser(ud.getUser());
		reportService.save(report);
		return "redirect:/reports/index";
	}

	@GetMapping("/create")
	public String create(@ModelAttribute  ReportForm reportForm, Model model) {
		model.addAttribute("reportForm", reportForm);
		return "report/form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Report report, ReportForm reportForm, Authentication loginUser, Model model) {
		CustomUserDetails ud = (CustomUserDetails) loginUser.getPrincipal();
		reportForm.setConversionService(conversionService);
		report = reportForm.toEntity();
		report.setUser(ud.getUser());
		reportService.save(report);
		return "redirect:/reports/index";
	}
	@PostMapping("/delete")
	public String delete(@ModelAttribute Report report, Long id, Authentication loginUser, Model model) {
		CustomUserDetails ud = (CustomUserDetails) loginUser.getPrincipal();
		User user = ud.getUser();
		report = reportService.verifyReport(id);
		if  (report.getUser().getId() == user.getId()) {
			reportService.delete(id);
		}
		return "redirect:/reports/index";
	}

}
