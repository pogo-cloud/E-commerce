package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.CheckOutCartRequestDto;
import com.example.ecomproject.DTO.ResponseDto.CartResponseDto;
import com.example.ecomproject.DTO.ResponseDto.ItemResponseDto;
import com.example.ecomproject.DTO.ResponseDto.OrderResponseDto;
import com.example.ecomproject.Entity.*;
import com.example.ecomproject.Exception.InvalidCustomerException;
import com.example.ecomproject.Repository.CardRepository;
import com.example.ecomproject.Repository.CartRepository;
import com.example.ecomproject.Repository.CustomerRepository;
import com.example.ecomproject.Repository.OrderedRepository;
import com.example.ecomproject.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    OrderedService orderedService;
    public CartResponseDto saveCart(int customerId, Item item){
         Customer customer=customerRepository.findById(customerId).get();
         Cart cart=customer.getCart();
         int newTotal=cart.getTotalCost()+ item.getRequiredQuantity()*item.getProduct().getPrice();
         cart.setTotalCost(newTotal);
         cart.getItemsList().add(item);
        cart.setNumberOfItem(cart.getItemsList().size());
        item.setCart(cart);
        Cart savedCart=cartRepository.save(cart);
        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .totalCost(savedCart.getTotalCost())
                .customerName(customer.getName())
                .numberOfItem(savedCart.getNumberOfItem())
                .build();
        List<ItemResponseDto> itemResponseDtoList=new ArrayList<>();
        for(Item items:savedCart.getItemsList()){
            ItemResponseDto itemResponseDto= ItemTransformer.ItemToItemResponseDto(item);
            itemResponseDtoList.add(itemResponseDto);
        }
        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }
    public OrderResponseDto CheckOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("Customer is Not Present");
        }
        Card card=cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        if(card==null||card.getCvv()!=checkOutCartRequestDto.getCvv()||customer!=card.getCustomer()){
            throw new Exception("Invalid Card Of the Customer");
        }
        Cart cart=customer.getCart();
        if(cart.getNumberOfItem()==0){
            throw new Exception("Cart is Empty");
        }
        try{
            Ordered order=orderedService.placeOrder(customer,card);
            customer.getOrderList().add(order);
            Ordered saveOrder=orderedRepository.save(order);
            resetCart(cart);
            //decreaseOrder();
            OrderResponseDto orderResponseDto=new OrderResponseDto();
            orderResponseDto.setOrderNo(saveOrder.getOrderNo());
            orderResponseDto.setOrderDate(saveOrder.getOrderDate());
            orderResponseDto.setCardUsed(saveOrder.getCardUsed());
            orderResponseDto.setTotalValue(saveOrder.getTotalValue());
            orderResponseDto.setCustomerName(customer.getName());
            List<ItemResponseDto>items=new ArrayList<>();
            for(Item item:saveOrder.getItems()){
                items.add(ItemTransformer.ItemToItemResponseDto(item));
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }


    }
    public void resetCart(Cart cart){
        cart.setTotalCost(0);
        for(Item item: cart.getItemsList()){
            item.setCart(null);
        }
        cart.setItemsList(new ArrayList<>());
        cart.getItemsList().clear();
    }

}
