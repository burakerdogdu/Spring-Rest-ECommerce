package com.works.repositories;

import com.works.entities.Product;
import com.works.entities.projection.IProCat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select P.PID, P.TITLE, P.DETAIL, P.PRICE,P.STOCK,P.BRAND,I.path,C.CID,C.CATEGORY from PRODUCT as P \n"+
            "inner join PRODUCT_CATEGORIES PC on P.PID = PC.PRODUCT_PID \n"+
            "inner join CATEGORY as C on C.CID = PC.CATEGORIES_CID \n"+
            "inner join PRODUCT_IMAGES as PI on PI.product_pid = P.pid \n "+
            "inner join IMAGES as I on I.iid = PI.images_iid where C.cid =?1",nativeQuery = true)
    Page<IProCat> proCatJoin(Long cid, Pageable pageable);
}