package com.book_library.book_library.repositories;

import com.book_library.book_library.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
   Optional<UserEntity> findByUsername(String username);

}
