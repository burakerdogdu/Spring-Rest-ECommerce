package com.works.services;

import com.works.configs.Rest;
import com.works.entities.Category;
import com.works.entities.Images;
import com.works.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;


    public ResponseEntity getcategory(String category){
        Optional<Category> category1= categoryRepository.findByCategoryEquals(category);
        Rest rest = new Rest(true,category1);
        return new ResponseEntity(rest,HttpStatus.OK);

    }
    public ResponseEntity save (Category category){
        try {
            categoryRepository.save(category);
            Rest rest = new Rest(true, category);
            return new ResponseEntity(rest,HttpStatus.OK);
        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity update (Category category){
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(category.getCid());
            if (optionalCategory.isPresent()){
                categoryRepository.saveAndFlush(category);
                Rest rest = new Rest(true, category);
                return new ResponseEntity(rest,HttpStatus.OK);
            }
            else {
                Rest rest = new Rest(false,"girilen kategori değeri bulunamadı! ");
                return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity delete (Long cid){
        try {
            categoryRepository.deleteById(cid);
            Rest rest = new Rest(true, cid);
            return new ResponseEntity(rest,HttpStatus.OK);
        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity list (){
            List<Category> ls = categoryRepository.findAll();
            Rest rest = new Rest(true, ls);
            return new ResponseEntity(rest,HttpStatus.OK);
    }
}
