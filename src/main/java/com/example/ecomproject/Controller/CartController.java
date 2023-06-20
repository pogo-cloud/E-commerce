package com.example.ecomproject.Controller;

import com.example.ecomproject.DTO.RequestDto.CheckOutCartRequestDto;
import com.example.ecomproject.DTO.RequestDto.ItemRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CartResponseDto;
import com.example.ecomproject.DTO.ResponseDto.OrderResponseDto;
import com.example.ecomproject.Entity.Item;
import com.example.ecomproject.Service.CartService;
import com.example.ecomproject.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {
         try{
            Item savedItem=itemService.addToItem(itemRequestDto);
             CartResponseDto cartResponseDto=cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
             return new ResponseEntity<>(cartResponseDto, HttpStatus.CREATED);
         }catch(Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
         }
    }
    @PostMapping("/checkout")
    public OrderResponseDto CheckOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        return cartService.CheckOutCart(checkOutCartRequestDto);
    }

}
