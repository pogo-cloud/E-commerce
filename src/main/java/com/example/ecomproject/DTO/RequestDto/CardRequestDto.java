package com.example.ecomproject.DTO.RequestDto;

import com.example.ecomproject.Enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    int customerId;
    String cardNo;

    Integer cvv;
    Date expiryDate;
    CardType cardType;
}
