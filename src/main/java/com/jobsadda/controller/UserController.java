package com.jobsadda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	 
	@PostMapping("/registerUser")
	public ResponseEntity<UserResponeDTO> registerUser(@RequestBody @Valid UserRequestDTO requestDTO){
		log.info("user details {}",requestDTO);
		UserResponeDTO userRespone=new UserResponeDTO();
		userRespone=userService.registerUser(requestDTO);
		return new ResponseEntity<UserResponeDTO>(userRespone,HttpStatus.CREATED);
	}
}
