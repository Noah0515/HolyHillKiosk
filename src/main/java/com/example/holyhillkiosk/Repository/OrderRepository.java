package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.Orders;
import com.example.holyhillkiosk.Entity.OrdersId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Orders, OrdersId> {
    @Override
    List<Orders> findAll();
    Optional<Orders> findById(OrdersId id);
    @Transactional
    @Modifying
    @Query(value = """
    UPDATE orders
    SET ordercomplete = true
    WHERE ordertime = :ordertime AND orderid = :orderid
    """, nativeQuery = true)
    void updateOrderCompletion(@Param("ordertime") Timestamp ordertime, @Param("orderid") String orderid);
}
