package com.book_library.book_library.repositories;


import com.book_library.book_library.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    boolean existsProductByproductName(String productName);
//    Optional<Products> findByName(String productName);
}
