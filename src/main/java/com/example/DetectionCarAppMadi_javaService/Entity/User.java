package com.example.DetectionCarAppMadi_javaService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно содержать от 3 до 50 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя пользователя должно содержать только русские и английские буквы")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 5, message = "Пароль должен содержать минимум 5 символов")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).+$", message = "Пароль должен содержать хотя бы одну букву и одну цифру")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат email")
    @Column(name = "email")
    private String email;
}