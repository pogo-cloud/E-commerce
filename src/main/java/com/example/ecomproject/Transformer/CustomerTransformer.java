package com.example.ecomproject.Transformer;

import com.example.ecomproject.DTO.RequestDto.CustomerRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecomproject.Entity.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }
    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome to MyCart"+customer.getName())
                .build();

    }
}
