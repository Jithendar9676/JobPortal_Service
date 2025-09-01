package com.jobsadda.dto;

import com.jobsadda.entity.User;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

	private Long id;
	private String name;
	private String email;
	private String password;
	private AccountType accountType;
	
}
