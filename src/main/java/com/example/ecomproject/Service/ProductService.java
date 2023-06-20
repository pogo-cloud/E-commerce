package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.ProductRequestDto;
import com.example.ecomproject.DTO.ResponseDto.ProductResponseDto;
import com.example.ecomproject.Entity.Item;
import com.example.ecomproject.Entity.Product;
import com.example.ecomproject.Entity.Seller;
import com.example.ecomproject.Enums.ProductCategory;
import com.example.ecomproject.Enums.ProductStatus;
import com.example.ecomproject.Exception.SellerNotPresentException;
import com.example.ecomproject.Repository.ProductRepository;
import com.example.ecomproject.Repository.SellerRepository;
import com.example.ecomproject.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotPresentException {
       Seller seller;
       try{
           seller=sellerRepository.findById(productRequestDto.getSellerId()).get();
       }catch(Exception e){
           throw new SellerNotPresentException("seller is not Present");
       }
       Product product= ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
       product.setSeller(seller);
       seller.getProduct().add(product);
       sellerRepository.save(seller);
       ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
       return productResponseDto;
    }
    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory){
        List<Product>products=productRepository.findByProductCategory(productCategory);
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();
        for(Product product:products){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }
    public List<ProductResponseDto> getProductByPriceAndCategory(int price,String category){
        List<Product>products=productRepository.findByPriceAndCategory(price,category);
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();
        for(Product product:products){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }
    public void decreaseOrder(Item item) throws Exception {
        Product product=item.getProduct();
        int currentQuantity=product.getQuantity();
        int quantity= item.getRequiredQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Product Out Of Stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(currentQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }

}
