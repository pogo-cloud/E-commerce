package com.example.ecomproject.Service;

import com.example.ecomproject.DTO.RequestDto.SellerRequestDto;
import com.example.ecomproject.DTO.ResponseDto.SellerResponseDto;
import com.example.ecomproject.Entity.Seller;
import com.example.ecomproject.Exception.EmailAlreadyPresentException;
import com.example.ecomproject.Repository.SellerRepository;
import com.example.ecomproject.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService{
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {
        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null){
            throw new EmailAlreadyPresentException("email is already present");
        }
        Seller seller= SellerTransformer.SellerRequestDtotoSeller(sellerRequestDto);
        Seller savedSeller=sellerRepository.save(seller);

        SellerResponseDto sellerResponseDto=SellerTransformer.SellertoSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }
    public SellerResponseDto getSellerByEmail(String emailId) throws Exception {
        Seller seller=sellerRepository.findByEmailId(emailId);
        if (seller != null) {
            SellerResponseDto sellerResponseDto = SellerTransformer.SellertoSellerResponseDto(seller);
            return sellerResponseDto;
        }
        // Handle the case when no seller is found with the given emailId
        // You can throw an exception, return null, or handle it according to your application's requirements.
        throw new Exception("Seller not found");
    }
    public SellerResponseDto getSellerById(int id) throws Exception {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isPresent()) {
            Seller seller = sellerOptional.get();
            SellerResponseDto sellerResponseDto = SellerTransformer.SellertoSellerResponseDto(seller);
            return sellerResponseDto;
        }

        throw new Exception("Seller is not Present");

    }
    public List<SellerResponseDto> getAllSeller(){
         List<Seller>sellers=sellerRepository.findAll();
         List<SellerResponseDto>sellerResponseDto=new ArrayList<>();
         for(Seller seller:sellers){
             SellerResponseDto sellerResponseDto1=SellerTransformer.SellertoSellerResponseDto(seller);
             sellerResponseDto.add(sellerResponseDto1);
         }
         return sellerResponseDto;
    }
    public void deleteSellerByEmail(String email) throws Exception {
        Seller seller=sellerRepository.findByEmailId(email);
        if(seller!=null){
            sellerRepository.delete(seller);
        }else{
            throw new Exception("Seller not found");
        }

    }
}
