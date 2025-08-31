package com.jobsadda.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

	private String id;
	private String name;
	private String email;
	private String password;
	private AccountType accountType;
}
