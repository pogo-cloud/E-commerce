package com.example.ecomproject.Transformer;

import com.example.ecomproject.DTO.RequestDto.ItemRequestDto;
import com.example.ecomproject.DTO.ResponseDto.ItemResponseDto;
import com.example.ecomproject.Entity.Item;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .priceOfOneItem(item.getProduct().getPrice())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .quantity(item.getRequiredQuantity())
                .build();

    }
}
