package com.works.repositories;

import com.works.entities.Orders;
import com.works.entities.projection.IProCat;
import com.works.entities.projection.IUserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select U.NAME, U.SURNAME, U.EMAIL,U.AGE,U.ADRESS,O.PRODUCTNAME,O.PID,O.OID from USER as U\n" +
            "    inner join ORDERS_USERS as UO on U.UID = UO.USERS_UID\n" +
            "                inner join ORDERS as O on O.OID = UO.ORDERS_OID\n" +
            "                where U.UID =?1",nativeQuery = true)
    List<IUserOrder> orderUserJoin(Long uid);

    @Query(value = "select U.NAME, U.SURNAME, U.EMAIL,U.AGE,U.ADRESS,O.PRODUCTNAME,O.PID,O.OID from USER as U\n" +
            "    inner join ORDERS_USERS as UO on U.UID = UO.USERS_UID\n" +
            "                inner join ORDERS as O on O.OID = UO.ORDERS_OID\n" +
            "                ",nativeQuery = true)
    Page<IUserOrder> OrderUserJoin(Pageable pageable);
}