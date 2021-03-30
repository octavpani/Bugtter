package com.example.bugtter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="status")
public class Status {

	@Column(name="id")
	@Id
	private Long id;

	@Column(name="name")
	private String name;

	@OneToMany(mappedBy="status")
	private List<Report> report;

}
