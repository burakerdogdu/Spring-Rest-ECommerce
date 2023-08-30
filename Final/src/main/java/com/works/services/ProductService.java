package com.works.services;

import com.works.configs.Rest;
import com.works.entities.Category;
import com.works.entities.Product;
import com.works.entities.projection.IProCat;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;


    public ResponseEntity save (Product product){
        try {
            productRepository.save(product);
            Rest rest = new Rest(true, product);
            return new ResponseEntity(rest, HttpStatus.OK);
        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity update (Product product){
        try {
            Optional<Product> optionalProduct = productRepository.findById(product.getPid());
            if (optionalProduct.isPresent()){
                productRepository.saveAndFlush(product);
                Rest rest = new Rest(true, product);
                return new ResponseEntity(rest,HttpStatus.OK);
            }
            else {
                Rest rest = new Rest(false,"girilen ürün değeri bulunamadı! ");
                return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity delete (Long pid){
        try {
            productRepository.deleteById(pid);
            Rest rest = new Rest(true, pid);
            return new ResponseEntity(rest,HttpStatus.OK);
        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity list (){
        List<Product> ls = productRepository.findAll();
        Rest rest = new Rest(true, ls);
        return new ResponseEntity(rest,HttpStatus.OK);
    }
    public boolean stockRefresh(Long pid){
       Optional<Product> optionalProduct = productRepository.findById(pid);
       if(optionalProduct.get().getStock()>0){
           Integer Stock = optionalProduct.get().getStock();
           optionalProduct.get().setStock(Stock-1);
           productRepository.saveAndFlush(optionalProduct.get());
           return true;
       }
       return false;
    }
     public ResponseEntity singleProduct(Long pid){
       Optional<Product> optionalProduct = productRepository.findById(pid);
         Rest rest = new Rest(true,optionalProduct);
       if(optionalProduct.isPresent()){
           return new ResponseEntity(rest,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(rest,HttpStatus.OK);
       }
     }

     public ResponseEntity listCatPage(Long cid, int page){
        Sort sort = Sort.by("cid").ascending();
        Pageable pageable = PageRequest.of(page, 3);
        Page<IProCat> proCatPage =  productRepository.proCatJoin(cid,pageable);
        return new ResponseEntity(proCatPage,HttpStatus.OK);
    }

}
