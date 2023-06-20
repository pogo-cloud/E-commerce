package com.example.ecomproject.Entity;

import com.example.ecomproject.Enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Card {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
int id;
@Column(unique = true)
String cardNo;

Integer cvv;
Date expiryDate;
@Enumerated(EnumType.STRING)
CardType cardType;

@ManyToOne
@JoinColumn
Customer customer;


}
