package com.example.ecomproject.Transformer;

import com.example.ecomproject.DTO.RequestDto.SellerRequestDto;
import com.example.ecomproject.DTO.ResponseDto.SellerResponseDto;
import com.example.ecomproject.Entity.Seller;

public class SellerTransformer {
    public static Seller SellerRequestDtotoSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder().name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }
    public static SellerResponseDto SellertoSellerResponseDto(Seller seller){
        return SellerResponseDto.builder().name(seller.getName()).age(seller.getAge()).build();
    }
}
