package com.book_library.book_library.controllers;

import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.models.Cart;
import com.book_library.book_library.models.Product;
import com.book_library.book_library.models.UserEntity;
import com.book_library.book_library.security.userDetails.CustomUserDetails;
import com.book_library.book_library.services.CartService;
import com.book_library.book_library.services.ProductService;
import com.book_library.book_library.services.UserEntityService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    private static CartService cartService;
    private static ProductService productService;
    private static UserEntityService userEntityService;

    public CartController (CartService cartService, ProductService productService,
                                      UserEntityService userEntityService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userEntityService = userEntityService;
    }



    @PostMapping("/cart/add")
    public String addCart(@RequestParam("product_id") int product_id, Principal principal, Model model) {
        Optional<UserEntity> userEntity = userEntityService.FindUserByUsername(principal.getName());
        Product product = productService.getProductById(product_id);

        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            cartService.addToCart(user, product);
        }

        return "redirect:/Products";
    }

    @PostMapping("/cart/remove")
    public String removeCart(@RequestParam("product_id") int product_id, Principal principal) {
        Optional<UserEntity> userEntity = userEntityService.FindUserByUsername(principal.getName());
        Product product = productService.getProductById(product_id);

        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            cartService.removeFromCart(user, product);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        UserEntityDto userEntityDto = customUserDetails.userEntityDto();

        List<Cart> cart = cartService.getAllCarts(userEntityDto);
        List<Product> cartProducts = cart.stream()
                .map(Cart :: getProduct)
                .toList();

        model.addAttribute("cart", cartProducts);
        model.addAttribute("userEntityDto", userEntityDto);

        return "cart";
    }

}
