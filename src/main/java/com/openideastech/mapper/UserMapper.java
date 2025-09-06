package com.openideastech.mapper;

import com.openideastech.dto.UserRequestDTO;
import com.openideastech.dto.UserResponseDTO;
import com.openideastech.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(UserRequestDTO dto);

  UserResponseDTO toResponseDTO(User user);

}