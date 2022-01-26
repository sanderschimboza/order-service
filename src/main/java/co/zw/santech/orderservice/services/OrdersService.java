package co.zw.santech.orderservice.services;

import co.zw.santech.orderservice.models.Order;
import co.zw.santech.orderservice.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrdersService {

    private final
    OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrdersService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
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

    public ResponseEntity<?> processStockOrder(Order order) {
        try {
            orderRepository.updateOrder(order.getPrice(), order.getTotalPrice(),
                    order.getStatus(), order.getAvailable(), order.getOrderId());
            return ResponseEntity.ok(modelMapper.map(order, Order.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }
}
