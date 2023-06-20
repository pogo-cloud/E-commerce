package com.example.ecomproject.Transformer;

import com.example.ecomproject.DTO.RequestDto.CardRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CardResponseDto;
import com.example.ecomproject.Entity.Card;

public class CardTransformer {
    public static Card CardRequestDtotoCard(CardRequestDto cardRequestDto){
        return Card.builder()
                        .cardNo(cardRequestDto.getCardNo())
                                .cvv(cardRequestDto.getCvv())
                                        .expiryDate(cardRequestDto.getExpiryDate())
                                                .cardType(cardRequestDto.getCardType())
                .build();
    }
    public static CardResponseDto CardtoCardResponseDto(Card card){
        return CardResponseDto.builder().cardNo(card.getCardNo()).customerName(card.getCustomer().getName()).build();
    }
}
