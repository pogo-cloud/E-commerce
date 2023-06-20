package com.example.ecomproject.DTO.RequestDto;

import com.example.ecomproject.Enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    int SellerId;
    String name;

    int price;

    int quantity;
    ProductCategory productCategory;

}
