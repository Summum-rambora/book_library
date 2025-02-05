package com.book_library.book_library.repositories;


import com.book_library.book_library.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    boolean existsProductByproductName(String productName);
    List<Product> findAll();
//    Optional<Products> findByName(String productName);
}
