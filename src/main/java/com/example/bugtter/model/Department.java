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
@Table(name="department")
public class Department {

	@Column(name="department_id")
	@Id
	private Long id;

	@Column(name="name")
	private String name;

	@OneToMany(mappedBy="department")
	private List<User> users;

}
