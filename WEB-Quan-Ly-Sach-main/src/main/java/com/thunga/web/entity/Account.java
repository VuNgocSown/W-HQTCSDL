package com.thunga.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.thunga.web.form.AccountForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "decryptedpasword")
	private String decryptedPassword;
	
	public String getDecryptedPassword() {
		return this.decryptedPassword;
	}
	
	private String email;
	private String role;
	
	public String getRole() {
		return this.role;
	}
	
	private String created_at;
	private String updated_at;
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public Account (AccountForm accountForm) {
		this.username = accountForm.getUserName();
		this.decryptedPassword = accountForm.getPassword();
		this.email = accountForm.getEmail();
		this.role = "ROLE_USER";
	}

}
