package com.example.ecomproject.DTO.ResponseDto;

import com.example.ecomproject.Enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;

    String sellerName;

    int quantity;

    ProductStatus productStatus;
}
