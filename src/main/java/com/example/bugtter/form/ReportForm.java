package com.example.bugtter.form;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bugtter.model.Report;
import com.example.bugtter.service.StatusService;

import lombok.Data;

@Data
@Component
public class ReportForm {

		@Autowired
		private StatusService statusService;

		private Long id;

		private String title;

		private String content;

		private Long status;

		private Integer urgency;

		public ReportForm() {
			id = null;
			title = "";
			content  = "";
			status = null;
			urgency = null;
		}

		public ReportForm(Report report) {
			this.id = report.getId();
			this.title = report.getTitle();
			this.content = report.getContent();
			this.status = report.getStatus().getId();
			this.urgency = report.getUrgency();
		}

		public Report toEntity() {
			Report report = new Report();
			//User is created by AuthenticationObject
			report.setStatus(statusService.verifyStatus(status));
			report.setCreateTime(LocalDateTime.now());
			report.setId(id);
			report.setTitle(title);
			report.setContent(content);
			report.setUrgency(urgency);
			return report;
		}

		public void setStatusService(StatusService statusService) {
			this.statusService = statusService;
		}



	}
