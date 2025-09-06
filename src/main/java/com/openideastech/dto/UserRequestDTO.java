package com.openideastech.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDTO {

  @NotBlank(message = "O nome é obrigatório")
  @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres")
  private String name;

  @NotBlank(message = "O email é obrigatório")
  @Email(message = "O email deve ser válido")
  private String email;

  @NotBlank(message = "O nome de usuário é obrigatório")
  @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre {min} e {max} caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "O nome de usuário só pode conter letras, números, pontos, hífens e underscores")
  private String username;

  @NotBlank(message = "O número de celular é obrigatório")
  @Pattern(regexp = "\\d{11}", message = "O número de celular deve ter 11 dígitos")
  private String phoneNumber;

  @NotBlank(message = "A senha é obrigatória")
  @Size(min = 8, message = "A senha deve ter no mínimo {min} caracteres")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?*])(?=\\S+$).{8,}$", message = "A senha deve conter: 1 letra maiúscula (A-Z), 1 minúscula (a-z), 1 número (0-9), 1 especial (@#$%^&+=!?*) e sem espaços")
  private String password;

  @AssertTrue(message = "Você deve aceitar os termos e condições")
  private boolean termsAccepted;

}