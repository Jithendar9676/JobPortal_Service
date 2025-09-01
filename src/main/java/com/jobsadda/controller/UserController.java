package com.jobsadda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	public ResponseEntity<UserResponeDTO> registerUser(@RequestBody UserRequestDTO requestDTO){
		UserResponeDTO userRespone=new UserResponeDTO();
		userRespone=userService.registerUser(requestDTO);
		return new ResponseEntity<UserResponeDTO>(userRespone,HttpStatus.CREATED);
	}
}
