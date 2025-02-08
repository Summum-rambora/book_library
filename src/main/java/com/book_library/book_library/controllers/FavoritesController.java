package com.book_library.book_library.controllers;


import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.mappers.UserEntityMapper;
import com.book_library.book_library.models.Favorites;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.security.userDetails.CustomUserDetails;
import com.book_library.book_library.services.FavoritesService;
import com.book_library.book_library.services.ProductService;
import com.book_library.book_library.services.UserEntityService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class FavoritesController {

    private static FavoritesService favoritesService;
    private static ProductService productService;
    private static UserEntityService userEntityService;



    public FavoritesController(FavoritesService favoritesService, ProductService productService,
                               UserEntityService userEntityService) {
        this.favoritesService = favoritesService;
        this.productService = productService;
        this.userEntityService = userEntityService;
    }

    @PostMapping("/favorites/add")
    public String addFavorite(@RequestParam("product_id") int product_id, Principal principal ) {
        Optional<UserEntity> userEntity = userEntityService.FindUserByUsername(principal.getName());
        Product product = productService.getProductById(product_id);

        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            favoritesService.AddFavorite(user , product);
        }

        return "redirect:/Products";
    }


    @GetMapping("/favorites")
    public String ShowFavoritesPage(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        UserEntityDto userEntity = customUserDetails.userEntityDto();

        List<Favorites> favorites = favoritesService.findAllFavoritesByUserEntity(userEntity);

        List<Product> favoriteProducts = favorites.stream()
                        .map(Favorites :: getProduct)
                                .toList();

        model.addAttribute("favorites", favoriteProducts);
        model.addAttribute("userEntityDto", userEntity);

        return "favorites";
    }

}
