package com.example.ecomproject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {
    int customerId;
    int productId;

    int requiredQuantity;
    String CardNo;

    int cvv;

}
