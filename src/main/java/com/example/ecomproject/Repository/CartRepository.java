package com.example.ecomproject.Repository;

import com.example.ecomproject.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
