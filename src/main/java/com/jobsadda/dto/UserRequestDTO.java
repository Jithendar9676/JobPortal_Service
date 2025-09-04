package com.jobsadda.dto;




import jakarta.validation.constraints.NotBlank;


public class UserRequestDTO {

	private Long id;
	@NotBlank(message = "name is blank or null")
	private String name;
	@NotBlank(message = "email is blank or null")
	private String email;
	@NotBlank(message = "password is blank or null")
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public UserRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequestDTO(Long id, String name, String email, String password, AccountType accountType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "UserRequestDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", accountType=" + accountType + "]";
	}
	
	
}
