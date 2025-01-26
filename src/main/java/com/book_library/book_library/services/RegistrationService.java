package com.book_library.book_library.services;


import com.book_library.book_library.dto.RegistrationDto;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    UserEntityRepository userRepository;
    RegistrationDto registrationDto;
    @Autowired
    public RegistrationService(UserEntityRepository userRepository ) {
        this.userRepository = userRepository;
    }


    public String registerUser(RegistrationDto registrationDto) {

        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            return "Username is already in use";
        }
        if (registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(registrationDto.getName());
            userEntity.setSurname(registrationDto.getSurname());
            userEntity.setEmail(registrationDto.getEmail());
            String encodedPassword = new BCryptPasswordEncoder().encode(registrationDto.getPassword());
            userEntity.setPassword(encodedPassword);
            userRepository.save(userEntity);
        }else {
            return "Passwords do not match";
        }

        return "Registration Successful";
    }



}
