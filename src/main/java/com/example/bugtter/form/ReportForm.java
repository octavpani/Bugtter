package com.example.bugtter.form;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.example.bugtter.model.Report;
import com.example.bugtter.model.Status;

import lombok.Data;

@Data
@Component
public class ReportForm {

	/*@Autowired
	private StatusService statusService; */
	@Autowired
	private ConversionService conversionService;

	private Long id;

	private String title;

	private String content;

	private String status;

	private Integer urgency;

	public ReportForm() {
		conversionService = null;
		id = null;
		title = "";
		content = "";
		status = null;
		urgency = null;


	}

	public ReportForm(Report report) {
		this.id = report.getId();
		this.title = report.getTitle();
		this.content = report.getContent();
		this.status = Long.toString(report.getStatus().getId());
		this.urgency = report.getUrgency();
	}

	public Report toEntity() {
		Report report = new Report();
		//User is created by AuthenticationObject
		report.setStatus(conversionService.convert(status, Status.class));
		report.setCreateTime(LocalDateTime.now());
		report.setId(id);
		report.setTitle(title);
		report.setContent(content);
		report.setUrgency(urgency);
		return report;
	}

	public boolean isNew() {
		return id == null;
	}

	public void setConversionService(ConversionService conversionService) {
			this.conversionService = conversionService;;


	}
	}
