package co.zw.santech.orderservice.services;

import co.zw.santech.orderservice.models.Order;
import co.zw.santech.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final
    OrderRepository orderRepository;

    @Autowired
    public OrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Integer saveOrder(Order order) {
        try {
            orderRepository.save(order);
            return 200;
        } catch (Exception e) {
            return 403;
        }
    }

    public void processStockOrder(Order order) {
        orderRepository.updateOrder(order.getPrice(), order.getTotalPrice(),
                order.getStatus(), order.getAvailable(), order.getOrderId());
        return;
    }
}
