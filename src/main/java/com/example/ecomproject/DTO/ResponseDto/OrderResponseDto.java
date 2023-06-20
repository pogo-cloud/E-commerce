package com.example.ecomproject.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String orderNo;

    Date orderDate;

    int totalValue;

    String CardUsed;
    List<ItemResponseDto> items;

    String customerName;

}
