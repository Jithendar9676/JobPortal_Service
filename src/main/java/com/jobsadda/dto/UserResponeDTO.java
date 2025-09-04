package com.jobsadda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponeDTO {

	private Long id;
	private String name;
	private String email;
	private AccountType accountType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public UserResponeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponeDTO(Long id, String name, String email, AccountType accountType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "UserResponeDTO [id=" + id + ", name=" + name + ", email=" + email + ", accountType=" + accountType
				+ "]";
	}
	
	
}
