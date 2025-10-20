package com.jobsadda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsadda.dto.LogInDTO;
import com.jobsadda.dto.ResponseDTO;
import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.exception.JobsAddaException;
import com.jobsadda.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	 
	@PostMapping("/registerUser")
	public ResponseEntity<UserResponeDTO> registerUser(@RequestBody @Valid UserRequestDTO requestDTO) throws JobsAddaException{
		log.info("user details {}",requestDTO);
		UserResponeDTO userRespone=new UserResponeDTO();
		userRespone=userService.registerUser(requestDTO);
		return new ResponseEntity<UserResponeDTO>(userRespone,HttpStatus.CREATED);
	}
	
	@PostMapping("/logInUser")
	public ResponseEntity<UserResponeDTO> logInUser(@RequestBody @Valid LogInDTO logInDTO) throws JobsAddaException{
		log.info("Login details {}",logInDTO.toString());
		return new ResponseEntity<UserResponeDTO>(userService.logInUser(logInDTO),HttpStatus.OK);
	}
	@PostMapping("/sendOtp/{email}")
	public ResponseEntity<ResponseDTO>sendOtp(@PathVariable  @Email(message = "{user.email.invalid}")String email) throws Exception{
		userService.sendOtp(email);
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("OTP sent successfully."),HttpStatus.OK);
	}
	@GetMapping("/verifyOtp/{email}/{otp}")
	public ResponseEntity<ResponseDTO>VerifyOtp(@PathVariable @Email(message = "{user.email.invalid}") String email,@PathVariable @Pattern(regexp = "^[0-9]{6}$") String otp) throws JobsAddaException{
		userService.verifyOtp(email,otp);
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("OTP Verified successfully."),HttpStatus.OK);
	}
	@PostMapping("/changePassword")
	public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid LogInDTO logInDTO) throws JobsAddaException{
		return new ResponseEntity<ResponseDTO>(userService.changePassword(logInDTO),HttpStatus.OK);
	}
	
}
