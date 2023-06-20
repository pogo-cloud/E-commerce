package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.OrderRequestDto;
import com.example.ecomproject.DTO.ResponseDto.ItemResponseDto;
import com.example.ecomproject.DTO.ResponseDto.OrderResponseDto;
import com.example.ecomproject.Entity.*;
import com.example.ecomproject.Enums.ProductStatus;
import com.example.ecomproject.Exception.InvalidCustomerException;
import com.example.ecomproject.Repository.CardRepository;
import com.example.ecomproject.Repository.CustomerRepository;
import com.example.ecomproject.Repository.OrderedRepository;
import com.example.ecomproject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderedService {
    @Autowired
    CustomerService customerService;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderedRepository orderedRepository;

    public Ordered placeOrder(Customer customer, Card card) throws Exception {
        Ordered order=new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        Cart cart=customer.getCart();
        order.setCustomer(customer);
        String maskedCardNo=GenerateMaskedCardNo(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        List<Item> orderItemList=new ArrayList<>();
        for(Item item:cart.getItemsList()){
            try{
                productService.decreaseOrder(item);
                orderItemList.add(item);
            }catch(Exception e){
                   throw new Exception("!Product Out Of Stock");
            }
        }
        order.setItems(orderItemList);
        order.setTotalValue(cart.getTotalCost());
        return order;

    }
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }catch(Exception e){
            throw new InvalidCustomerException("Customer Doesn't Exist");
        }
        Product product;
        try{
            product=productRepository.findById(orderRequestDto.getProductId()).get();
        }catch(Exception e){
            throw new Exception("Product is not present");
        }
        if(orderRequestDto.getRequiredQuantity()>product.getQuantity()||product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product is Out Of Stock");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new Exception("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.decreaseOrder(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = GenerateMaskedCardNo(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order); // order and item

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setCustomerName(customer.getName());
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setTotalValue(savedOrder.getTotalValue());

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItems()){
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);
        return orderResponseDto;

    }
    public String GenerateMaskedCardNo(String cardNo){
        String str="";
        for(int i=0;i<cardNo.length()-4;i++){
            str+='X';
        }
        str+=cardNo.substring(cardNo.length()-4);
        return str;
    }

}

