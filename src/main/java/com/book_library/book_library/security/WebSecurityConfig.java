package com.book_library.book_library.security;


import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.UserEntityRepository;
import com.book_library.book_library.security.userDetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    CustomUserDetailsService userDetailsService;
    UserEntityRepository userEntityRepository;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDetailsService, UserEntityRepository userEntityRepository) {
        this.userDetailsService = userDetailsService;
        this.userEntityRepository = userEntityRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain chain(HttpSecurity http ) throws Exception {
        http
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests
                            .requestMatchers(HttpMethod.GET, "/registration").permitAll()
                            .requestMatchers(HttpMethod.POST, "/registration").permitAll()
                            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                            .anyRequest().authenticated();
                })



                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));



    return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) throws Exception {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authProvider);

    }

    @Bean
    public CommandLineRunner initDatabase(){
        return args ->
        {
            if (userEntityRepository.findByUsername("testUser").isEmpty()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername("testUser");
                userEntity.setPassword(passwordEncoder().encode("password"));
                userEntityRepository.save(userEntity);
                System.out.println("Created TEST user");
            }
            else {
                System.out.println("User already exists");
            }
        };
    }






}
