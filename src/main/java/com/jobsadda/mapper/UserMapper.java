package com.jobsadda.mapper;

import org.mapstruct.Mapper;

import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserResponeDTO toDTO(User user);
	User toEntity(UserRequestDTO userRequestDTO);
}
