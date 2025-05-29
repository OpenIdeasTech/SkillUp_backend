package com.openideastech.controller;

import com.openideastech.dto.UserRequestDTO;
import com.openideastech.dto.UserResponseDTO;
import com.openideastech.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Criar um novo usuário")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    @ApiResponse(responseCode = "409", description = "Dados já cadastrados")
  })
  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    log.info("Criando usuário com email: {}", userRequestDTO.getEmail());
    UserResponseDTO createdUser = userService.createUser(userRequestDTO);
    log.info("Usuário criado - ID: {}", createdUser.getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

}