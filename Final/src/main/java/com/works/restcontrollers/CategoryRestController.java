package com.works.restcontrollers;

import com.works.entities.Category;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryRestController {
    final CategoryService categoryService;


    @PostMapping("/save")
    public ResponseEntity save (@Valid @RequestBody Category category){
        return categoryService.save(category);
    }
    @GetMapping("/list")
    public ResponseEntity list (){
        return categoryService.list();
    }
    @GetMapping("/delete/{cid}")
    public ResponseEntity delete(@PathVariable Long cid){
        return categoryService.delete(cid);
    }
    @PostMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Category category){
        return categoryService.update(category);
    }

    @PostMapping("/category")
    public ResponseEntity getcategory(@RequestBody Category category){
        return categoryService.getcategory(category.getCategory());
    }
}
