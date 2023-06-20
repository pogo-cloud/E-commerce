package com.example.ecomproject.Controller;

import com.example.ecomproject.DTO.RequestDto.SellerRequestDto;
import com.example.ecomproject.DTO.ResponseDto.SellerResponseDto;
import com.example.ecomproject.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getSeller/{email}")
    public SellerResponseDto getSellerByEmail(@PathVariable("email") String emailId) throws Exception {
          return sellerService.getSellerByEmail(emailId);

    }
    @GetMapping("/getSellerId/{Id}")
    public SellerResponseDto getSellerById(@PathVariable("Id")int Id)throws Exception{
        return sellerService.getSellerById(Id);
    }
    @GetMapping("/getAllSeller")
    public ResponseEntity getAllSeller(){
        List<SellerResponseDto>AllsellerList=new ArrayList<>();
        AllsellerList=sellerService.getAllSeller();
        return new ResponseEntity<>(AllsellerList,HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/deleteSeller/{email}")
    public ResponseEntity<String> deleteSellerByEmail(@PathVariable("email") String email){
        try {
            sellerService.deleteSellerByEmail(email);
            return ResponseEntity.ok("seller deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting seller");
        }
    }

}
