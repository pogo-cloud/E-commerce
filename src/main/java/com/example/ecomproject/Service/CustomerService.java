package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.CustomerRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecomproject.Entity.Cart;
import com.example.ecomproject.Entity.Customer;
import com.example.ecomproject.Exception.MobileAlreadyPresentException;
import com.example.ecomproject.Repository.CustomerRepository;
import com.example.ecomproject.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileAlreadyPresentException {
        if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null){
            throw new MobileAlreadyPresentException("!Customer Already Present");
        }
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart=Cart.builder()
        .totalCost(0)
                .numberOfItem(0)
                .customer(customer)
        .build();
        customer.setCart(cart);
        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }
}
