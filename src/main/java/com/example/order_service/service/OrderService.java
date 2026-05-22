package com.example.order_service.service;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order createOrder(Order order) {

        double total = order.getPrice() * order.getQuantity();

        order.setTotalPrice(total);

        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order updateOrder(Long id, Order updatedOrder) {

        Order existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existing.setProductId(updatedOrder.getProductId());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setPrice(updatedOrder.getPrice());

        double total = updatedOrder.getPrice() * updatedOrder.getQuantity();

        existing.setTotalPrice(total);

        return repository.save(existing);
    }

    public String deleteOrder(Long id) {

        repository.deleteById(id);

        return "Order deleted successfully";
    }
}