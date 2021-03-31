package com.example.bugtter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User {


	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	@Id
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id", nullable=false, insertable = false, updatable = false)
	private Department department;

	@Column(name="role")
	private String role;

	@Lob
	@Column(name="avatar")
	private String  avatar;

	public User() {
	}

	public User(Long id, String username, String password, Department department, String role, String avatar) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.department = department;
		this.role = role;
		this.avatar = avatar;
	}

}
