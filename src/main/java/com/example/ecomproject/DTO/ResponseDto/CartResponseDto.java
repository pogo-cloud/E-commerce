package com.example.ecomproject.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    int totalCost;
    int numberOfItem;

    String customerName;

    List<ItemResponseDto> items;

}
