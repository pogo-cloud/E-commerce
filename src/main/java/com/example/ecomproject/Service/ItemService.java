package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.ItemRequestDto;
import com.example.ecomproject.Entity.Customer;
import com.example.ecomproject.Entity.Item;
import com.example.ecomproject.Entity.Product;
import com.example.ecomproject.Enums.ProductStatus;
import com.example.ecomproject.Exception.InvalidCustomerException;
import com.example.ecomproject.Repository.CustomerRepository;
import com.example.ecomproject.Repository.ItemRepository;
import com.example.ecomproject.Repository.ProductRepository;
import com.example.ecomproject.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CustomerRepository customerRepository;
    public Item addToItem(ItemRequestDto itemRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }catch(Exception e){
            throw new InvalidCustomerException("Customer Doesn't Exist");
        }
        Product product;
        try{
            product=productRepository.findById(itemRequestDto.getProductId()).get();
        }catch(Exception e){
            throw new Exception("Product is not present");
        }
        if(itemRequestDto.getRequiredQuantity()>product.getQuantity()||product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product is Out Of Stock");
        }
        Item item= ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setProduct(product);
        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}
