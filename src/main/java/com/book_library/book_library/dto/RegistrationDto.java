package com.book_library.book_library.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String name;
    private String surname;
    private String email;

    private String username;
    private String password;
    private String confirmPassword;
}
