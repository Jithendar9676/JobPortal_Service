package com.jobsadda.dto;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

	private Long id;
	@NotBlank(message = "{user.name.absent}")
	private String name;
	@NotBlank(message = "{user.email.absent}")
	@Email(message = "{user.email.invalid}")
	private String email;
	@NotBlank(message = "{user.password.absent}")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@!#\\$%\\^&\\*])[A-Za-z\\d@!#\\$%\\^&\\*]{8,15}$",message ="{Password must be 8-15 characters, with uppercase, lowercase, number and special character}")
	private String password;
	private AccountType accountType;	
}
