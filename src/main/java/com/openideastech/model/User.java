package com.openideastech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome é obrigatório")
  @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres")
  @Column(nullable = false, length = 100)
  private String name;

  @NotBlank(message = "O email é obrigatório")
  @Email(message = "O email deve ser válido")
  @Column(nullable = false, unique = true)
  private String email;

  @NotBlank(message = "O nome de usuário é obrigatório")
  @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre {min} e {max} caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "O nome de usuário só pode conter letras, números, pontos, hífens e underscores")
  @Column(nullable = false, unique = true, length = 20)
  private String username;

  @NotBlank(message = "O número de celular é obrigatório")
  @Pattern(regexp = "\\d{11}", message = "O número de celular deve ter 11 dígitos")
  @Column(name = "phone_number", nullable = false, unique = true, length = 11)
  private String phoneNumber;

  @Column(nullable = false, length = 60)
  private String passwordHash;

  @Column(name = "terms_accepted", nullable = false)
  private boolean termsAccepted = false;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = Instant.now();
  }

}