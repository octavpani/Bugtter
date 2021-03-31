package com.example.bugtter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

	@Entity
	@Table(name = "report")
	@Data
	public class Report {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	@Id
	private Long id;

	@NotNull
	@Column(name = "title")
	private String title;

	@NotNull
	@Column(name = "content")
	private String content;

	//ここの処理は、参照用としての宣言
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="status_id", nullable=false, insertable = false, updatable = false)
	private Status status;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", nullable=false, insertable = false, updatable = false)
	private User user;

	//キャストできるの？データベースはchar
	@NotNull
	@Column(name = "urgency")
	private int urgency;

	public Report() {
	}

	public Report(Long id, String title, String content, Status status, User user) {
	this.id = id;
	this.title = title;
	this.content = content;
	this.status = status;
	this.user = user;
	}
}