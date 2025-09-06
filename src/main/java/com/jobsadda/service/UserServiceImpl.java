package com.jobsadda.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobsadda.dto.LogInDTO;
import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.entity.User;
import com.jobsadda.exception.JobsAddaException;
import com.jobsadda.mapper.UserMapper;
import com.jobsadda.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log=LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public UserResponeDTO registerUser(UserRequestDTO userRequestDTO) throws JobsAddaException {
		Optional<User> userExist=userRepository.findByEmail(userRequestDTO.getEmail());
		if(userExist.isPresent())throw new JobsAddaException("USER_FOUND");
	      User user=userMapper.toEntity(userRequestDTO);
	      user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		return userMapper.toDTO(userRepository.save(user));
	}
	@Override
	public UserResponeDTO logInUser(LogInDTO logInDTO) throws JobsAddaException {
		User user=userRepository.findByEmail(logInDTO.getEmail()).orElseThrow(()->new JobsAddaException("LOGIN_FAILED"));
		if(!passwordEncoder.matches(logInDTO.getPassword(), user.getPassword())) throw new JobsAddaException("LOGIN_FAILED");
		return userMapper.toDTO(user);
	}
	

}
