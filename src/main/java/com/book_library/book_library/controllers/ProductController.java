package com.book_library.book_library.controllers;


import com.book_library.book_library.models.Product;
import com.book_library.book_library.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/addProducts")
    public String addProducts(Model model) {
        model.addAttribute("product", new Product());
        return "addProducts";
    }

    @PostMapping("/addProducts")
    public String addProduct(Product product, Model model) {
        productService.addNewProduct(product);
        return "redirect:/Products";
    }

    @GetMapping("/Products")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }


}
