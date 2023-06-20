package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.CardRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CardResponseDto;
import com.example.ecomproject.Entity.Card;
import com.example.ecomproject.Entity.Customer;
import com.example.ecomproject.Exception.InvalidCustomerException;
import com.example.ecomproject.Repository.CardRepository;
import com.example.ecomproject.Repository.CustomerRepository;
import com.example.ecomproject.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
          Customer customer=customerRepository.findById(cardRequestDto.getCustomerId()).get();
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }
          Card card= CardTransformer.CardRequestDtotoCard(cardRequestDto);
          card.setCustomer(customer);
          customer.getCards().add(card);
          customerRepository.save(customer);
          CardResponseDto cardResponseDto=CardTransformer.CardtoCardResponseDto(card);
          return cardResponseDto;

    }
}
