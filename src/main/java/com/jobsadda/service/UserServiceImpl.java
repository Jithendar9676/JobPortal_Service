package com.jobsadda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public UserResponeDTO registerUser(UserRequestDTO userRequestDTO) {
	      User user=userMapper.toEntity(userRequestDTO);
	          user=userRepository.save(user);
		return userMapper.toDTO(user);
	}
	

}
