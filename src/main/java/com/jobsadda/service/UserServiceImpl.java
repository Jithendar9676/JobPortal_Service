package com.jobsadda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsadda.controller.UserController;
import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.entity.User;
import com.jobsadda.mapper.UserMapper;
import com.jobsadda.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	
	private static final Logger log=LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public UserResponeDTO registerUser(UserRequestDTO userRequestDTO) {
		log.debug("user Service userRequestDTO {}",userRequestDTO);
	      User user=userMapper.toEntity(userRequestDTO);
	     log.debug("user Service {}",user);
	          user=userRepository.save(user);
		return userMapper.toDTO(user);
	}
	

}
