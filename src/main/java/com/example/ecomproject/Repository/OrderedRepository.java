package com.example.ecomproject.Repository;

import com.example.ecomproject.Entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
