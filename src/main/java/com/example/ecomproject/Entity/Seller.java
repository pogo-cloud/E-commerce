package com.example.ecomproject.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;
    @Column(unique = true)
    String emailId;
    String mobNo;

    @OneToMany(mappedBy="seller",cascade=CascadeType.ALL)
    List<Product> product=new ArrayList<>();
}
