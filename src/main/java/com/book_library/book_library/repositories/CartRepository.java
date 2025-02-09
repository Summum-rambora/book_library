package com.book_library.book_library.repositories;

import com.book_library.book_library.models.Cart;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserEntity(UserEntity userEntity);
    void deleteByUserEntityAndProduct(UserEntity userEntity, Product product);
}
