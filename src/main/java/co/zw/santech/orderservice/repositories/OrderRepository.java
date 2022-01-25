package co.zw.santech.orderservice.repositories;


import co.zw.santech.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET price =:price, total_price =:totalPrice, status =:status, available =:available WHERE order_id =:orderId",
            nativeQuery = true)
    void updateOrder(BigDecimal price, BigDecimal totalPrice, String status, Long available, Long orderId);
}


