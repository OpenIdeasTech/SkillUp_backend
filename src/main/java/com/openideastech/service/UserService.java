package com.openideastech.service;

import com.openideastech.dto.UserRequestDTO;
import com.openideastech.dto.UserResponseDTO;
import com.openideastech.exception.DuplicateResourceException;
import com.openideastech.mapper.UserMapper;
import com.openideastech.model.User;
import com.openideastech.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserService(
    UserRepository userRepository,
    UserMapper userMapper,
    PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
    validateUniqueEmail(userRequestDTO.getEmail());
    validateUniqueUsername(userRequestDTO.getUsername());
    validateUniquePhoneNumber(userRequestDTO.getPhoneNumber());

    User user = userMapper.toEntity(userRequestDTO);
    user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));
    User savedUser = userRepository.save(user);
    return userMapper.toResponseDTO(savedUser);
  }

  private void validateUniqueEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new DuplicateResourceException("Email já cadastrado");
    }
  }

  private void validateUniqueUsername(String username) {
    if (userRepository.existsByUsername(username)) {
      throw new DuplicateResourceException("Nome de usuário já em uso");
    }
  }

  private void validateUniquePhoneNumber(String phoneNumber) {
    if (userRepository.existsByPhoneNumber(phoneNumber)) {
      throw new DuplicateResourceException("Número de celular já cadastrado");
    }
  }

}