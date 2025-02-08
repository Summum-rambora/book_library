package com.book_library.book_library.repositories;

import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.models.Favorites;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findByUserEntity(UserEntity userEntity);
    Favorites deleteFavoritesByUserEntityAndProduct(UserEntity userEntity, Product product);

}
