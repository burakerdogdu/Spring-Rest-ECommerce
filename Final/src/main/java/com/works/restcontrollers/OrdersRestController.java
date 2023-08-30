package com.works.restcontrollers;

import com.works.entities.Orders;
import com.works.entities.Product;
import com.works.services.OrdersService;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersRestController {
    final OrdersService ordersService;
    final ProductService productService;
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Orders orders){

        boolean stockStatus = productService.stockRefresh(orders.getPid());
        if (stockStatus){
            return ordersService.save(orders);
        }else {
            return new ResponseEntity(stockStatus, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/list")
    public ResponseEntity list (){
        return ordersService.list();
    }
    @GetMapping("/userorderlist/{uid}")
    public ResponseEntity userorderlist(@PathVariable Long uid){
        return ordersService.listOrderUser(uid);
    }
    @GetMapping("/delete/{oid}")
    public ResponseEntity delete(@PathVariable Long oid){
        return ordersService.delete(oid);
    }
    @PostMapping("/adminorderlist")
    public ResponseEntity adminorderlist(@RequestParam(defaultValue = "0") int page){
        return ordersService.OrderlistPage(page);
    }
}
