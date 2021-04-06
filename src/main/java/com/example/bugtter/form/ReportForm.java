package com.example.bugtter.form;

import java.time.LocalDateTime;

import com.example.bugtter.model.Report;

import lombok.Data;

@Data
public class ReportForm {

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
			urgency = null;;
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
			report.setCreateTime(LocalDateTime.now());
			report.setId(id);
			report.setTitle(title);
			report.setContent(content);
			report.setUrgency(urgency);
			return report;
		}



	}
