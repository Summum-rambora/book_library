package com.book_library.book_library.services;

import com.book_library.book_library.models.Favorites;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoritesService{


    private final FavoriteRepository favoriteRepository;

    public FavoritesService(FavoriteRepository favoriteRepository) {this.favoriteRepository = favoriteRepository;}


    public void AddFavorite(UserEntity userEntity, Product product) {
        favoriteRepository.save(new Favorites(userEntity, product));
    }

    public void DeleteFavorite(UserEntity userEntity, Product product) {
        favoriteRepository.deleteFavoritesByUserEntityAndProduct(userEntity, product);
    }


}
