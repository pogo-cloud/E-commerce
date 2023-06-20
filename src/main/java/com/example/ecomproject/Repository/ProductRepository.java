package com.example.ecomproject.Repository;

import com.example.ecomproject.Entity.Product;
import com.example.ecomproject.Enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);
    @Query(value = "SELECT * FROM Product p WHERE p.price > :price AND p.product_category = :category", nativeQuery = true)
    List<Product> findByPriceAndCategory(int price, String category);
}
