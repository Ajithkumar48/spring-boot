package com.example.demo.order.service;

import com.example.demo.order.dto.OrderRequest;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderItem;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + orderRequest.getUserId()));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> orderItems = orderRequest.getItems().stream().map(itemRequest -> {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemRequest.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice()); // Or calculate price based on some logic
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
