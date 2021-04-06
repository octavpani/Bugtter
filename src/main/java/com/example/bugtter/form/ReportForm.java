package com.example.bugtter.form;

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



	}
