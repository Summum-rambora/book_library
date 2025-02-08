package com.book_library.book_library.dto;


import com.book_library.book_library.models.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEntityDto {
    private int id;

    private String username;
    private String password;

    public UserEntityDto(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.id = userEntity.getId();
    }
}
