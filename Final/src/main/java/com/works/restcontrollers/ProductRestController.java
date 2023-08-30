package com.works.restcontrollers;

import com.works.entities.Category;
import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {
    final ProductService productService;
    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity save (@Valid @RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/list")
    public ResponseEntity list (){
        return productService.list();
    }
    @GetMapping("/delete/{pid}")
    public ResponseEntity delete(@PathVariable Long pid){
        return productService.delete(pid);
    }
    @PostMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Product product){
        return productService.update(product);
    }
    @GetMapping("/detail/{pid}")
    public ResponseEntity detail(@PathVariable Long pid){return  productService.singleProduct(pid);}
    @CrossOrigin
    @GetMapping("/categorylist/{cid}")
    public ResponseEntity products (@PathVariable Long cid,@RequestParam(defaultValue = "0") int page){
        return productService.listCatPage(cid,page);
    }
}
