package com.book_library.book_library.services;

import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.mappers.UserEntityMapper;
import com.book_library.book_library.models.Favorites;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.FavoriteRepository;
import com.book_library.book_library.repositories.ProductRepository;
import com.book_library.book_library.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService{




    private final FavoriteRepository favoriteRepository;
    private UserEntityRepository userEntityRepository;
    private ProductRepository productRepository;

    public FavoritesService(FavoriteRepository favoriteRepository, UserEntityRepository userEntityRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userEntityRepository = userEntityRepository;
        this.productRepository = productRepository;
    }


    public void AddFavorite(UserEntity userEntity, Product product) {

        UserEntity persistentUser = userEntityRepository.findById(userEntity.getId()).
                orElseThrow(() -> new IllegalArgumentException("User not found"));

        favoriteRepository.save(new Favorites(persistentUser, product));
    }

    public void DeleteFavorite(UserEntity userEntity, Product product) {
        favoriteRepository.deleteFavoritesByUserEntityAndProduct(userEntity, product);
    }

    public List<Favorites> findAllFavoritesByUserEntity(UserEntityDto userEntity) {


        UserEntity persistentUser = userEntityRepository.findById(userEntity.getId()).
                orElseThrow(() -> new IllegalArgumentException("User not found"));

        return favoriteRepository.findByUserEntity(persistentUser);
    }


}
