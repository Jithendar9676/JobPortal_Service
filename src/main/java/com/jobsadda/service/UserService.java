package com.jobsadda.service;

import org.springframework.stereotype.Service;

import com.jobsadda.dto.LogInDTO;
import com.jobsadda.dto.ResponseDTO;
import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.exception.JobsAddaException;

import jakarta.validation.Valid;





@Service
public interface UserService {

	 UserResponeDTO registerUser(UserRequestDTO userRequestDTO) throws JobsAddaException;

	UserResponeDTO logInUser(LogInDTO logInDTO) throws JobsAddaException;

	Boolean sendOtp(String email) throws Exception;

	Boolean verifyOtp(String email, String otp)throws JobsAddaException;

	ResponseDTO changePassword(@Valid LogInDTO logInDTO) throws JobsAddaException;


}
