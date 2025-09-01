package com.jobsadda.service;

import org.springframework.stereotype.Service;

import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;



@Service
public interface UserService {

	 UserResponeDTO registerUser(UserRequestDTO userRequestDTO);
}
