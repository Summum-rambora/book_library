package com.book_library.book_library.services;


import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.models.Cart;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.CartRepository;
import com.book_library.book_library.repositories.ProductRepository;
import com.book_library.book_library.repositories.UserEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private static CartRepository cartRepository;
    private static ProductRepository productRepository;
    private static UserEntityRepository userEntityRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository,
                       UserEntityRepository userEntityRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userEntityRepository = userEntityRepository;
    }



    public void addToCart(UserEntity userEntity, Product product) {
        UserEntity persistentUser = userEntityRepository.findById(userEntity.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartRepository.save(new Cart(persistentUser, product));
    }


    @Modifying
    @Transactional
    public void removeFromCart(UserEntity userEntity, Product product) {
        cartRepository.deleteByUserEntityAndProduct(userEntity, product);
    }

    public List<Cart> getAllCarts(UserEntityDto userEntity) {
        UserEntity persistentUser = userEntityRepository.findById(userEntity.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUserEntity(persistentUser);
    }



}
