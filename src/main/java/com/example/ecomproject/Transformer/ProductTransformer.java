package com.example.ecomproject.Transformer;

import com.example.ecomproject.DTO.RequestDto.ProductRequestDto;
import com.example.ecomproject.DTO.ResponseDto.ProductResponseDto;
import com.example.ecomproject.Entity.Product;
import com.example.ecomproject.Enums.ProductStatus;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .build();
    }
}
