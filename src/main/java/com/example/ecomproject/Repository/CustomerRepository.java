package com.example.ecomproject.Repository;

import com.example.ecomproject.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
     Customer findByMobNo(String mobNo);
}
