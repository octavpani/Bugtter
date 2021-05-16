package com.example.bugtter.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.example.bugtter.model.Report;
import com.example.bugtter.model.Status;

import lombok.Data;

@Data
@Component
public class ReportForm {


	@Autowired
	private ConversionService conversionService;

	private Long id;

	@Size(max= 30, min= 1, message = "Titleは、1文字以上30文字以内で入力してね。")
	private String title;

	@Size(max= 140, min= 1, message = "Contentは、1文字以上140文字以内で入力してね。")
	private String content;

	@Min(1)
	@Max(3)
	@NotNull
	private String status;

	@Min(1)
	@Max(5)
	@NotNull
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
