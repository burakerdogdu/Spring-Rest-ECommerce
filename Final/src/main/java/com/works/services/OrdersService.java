package com.works.services;

import com.works.configs.Rest;
import com.works.entities.Orders;
import com.works.entities.Product;
import com.works.entities.projection.IProCat;
import com.works.entities.projection.IUserOrder;
import com.works.repositories.OrdersRepository;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
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
public class OrdersService {
    final OrdersRepository ordersRepository;

    public ResponseEntity list(){
        List<Orders> ls = ordersRepository.findAll();
        Rest rest = new Rest(true,ls);
        return new ResponseEntity(rest, HttpStatus.OK);
    }
    public ResponseEntity save(Orders orders){
        ordersRepository.save(orders);
        Rest rest = new Rest(true,orders);
        return new ResponseEntity(rest,HttpStatus.OK);
    }
    public ResponseEntity listOrderUser(Long uid){
        List<IUserOrder> ls = ordersRepository.orderUserJoin(uid);
        return new ResponseEntity(ls,HttpStatus.OK);
    }
    public ResponseEntity delete(Long oid){
        ordersRepository.deleteById(oid);
        Rest rest = new Rest(true,oid);
        return new ResponseEntity<>(rest,HttpStatus.OK);
    }

    public ResponseEntity OrderlistPage( int page){
        Sort sort = Sort.by("cid").ascending();
        Pageable pageable = PageRequest.of(page, 3);
        Page<IUserOrder> proCatPage =  ordersRepository.OrderUserJoin(pageable);
        return new ResponseEntity(proCatPage,HttpStatus.OK);
    }
}
