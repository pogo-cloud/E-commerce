package com.example.ecomproject.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name="ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;
    @CreationTimestamp
    Date orderDate;

    int totalValue;

    String CardUsed;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> items =new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;


}
