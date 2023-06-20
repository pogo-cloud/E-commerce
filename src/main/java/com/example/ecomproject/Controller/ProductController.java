package com.example.ecomproject.Controller;

import com.example.ecomproject.DTO.RequestDto.ProductRequestDto;
import com.example.ecomproject.DTO.ResponseDto.ProductResponseDto;
import com.example.ecomproject.Enums.ProductCategory;
import com.example.ecomproject.Exception.SellerNotPresentException;
import com.example.ecomproject.Repository.ProductRepository;
import com.example.ecomproject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerNotPresentException {

        return productService.addProduct(productRequestDto);
    }
    @GetMapping("/get/{productCategory}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        return productService.getProductByCategory(productCategory);
    }
    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto>  getProductByPriceAndCategory(@PathVariable("price") int price,@PathVariable("category")String productCategory){
        return productService.getProductByPriceAndCategory(price,productCategory);
    }
}
