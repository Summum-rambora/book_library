package com.book_library.book_library.services;

import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {

    UserEntityRepository userEntityRepository;

    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    public Optional<UserEntity> FindUserByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }


}
