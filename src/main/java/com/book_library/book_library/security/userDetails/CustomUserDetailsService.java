package com.book_library.book_library.security.userDetails;


import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserEntityRepository userEntityRepository;
    public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("No user found with username: " + username));

        UserEntityDto userEntityDto = new UserEntityDto(userEntity);

        return new CustomUserDetails(userEntityDto);
    }
}
