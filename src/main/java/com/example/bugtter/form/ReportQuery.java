package com.example.bugtter.form;

import lombok.Data;

@Data
public class ReportQuery {
	private String title;
	private Integer urgency;
	private Integer status_Id;

	public ReportQuery() {
		title = "";
		urgency = 0;
		status_Id = 0;
	}

	public ReportQuery(String title, Integer urgency, Integer status_Id) {
		this.title = title;
		this.urgency = urgency;
		this.status_Id = status_Id;
	}

}
