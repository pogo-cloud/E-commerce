package com.example.ecomproject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class SellerRequestDto {

   String name;
    int age;
    String emailId;
    String mobNo;
}
