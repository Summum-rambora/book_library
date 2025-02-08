package com.book_library.book_library.services;


import com.book_library.book_library.models.Product;
import com.book_library.book_library.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product addNewProduct(Product product) {
        if
            (productRepository.existsProductByproductName(product.getProductName())){
            throw new IllegalArgumentException("Product already exists");
        }
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }

}
