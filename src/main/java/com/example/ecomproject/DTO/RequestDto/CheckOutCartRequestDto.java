package com.example.ecomproject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CheckOutCartRequestDto {
    String CardNo;

    int cvv;

    int customerId;
}
