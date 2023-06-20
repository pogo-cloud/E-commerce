package com.example.ecomproject.Controller;

import com.example.ecomproject.DTO.RequestDto.CustomerRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecomproject.Exception.MobileAlreadyPresentException;
import com.example.ecomproject.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileAlreadyPresentException {
       return customerService.addCustomer(customerRequestDto);

    }
}
