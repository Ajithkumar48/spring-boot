package com.example.demo.order.service;

import com.example.demo.order.dto.OrderItemRequest;
import com.example.demo.order.dto.OrderRequest;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order()));
        List<Order> orders = orderService.getAllOrders();
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
    }

    @Test
    void getOrderById() {
        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Optional<Order> result = orderService.getOrderById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void createOrder() {
        User user = new User("Test User", "test@example.com");
        Product product = new Product("Test Product", 10.0);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(1L);
        OrderItemRequest itemRequest = new OrderItemRequest();
        itemRequest.setProductId(1L);
        itemRequest.setQuantity(2);
        orderRequest.setItems(List.of(itemRequest));

        Order created = orderService.createOrder(orderRequest);

        assertNotNull(created);
        assertEquals("CREATED", created.getStatus());
        assertEquals(1, created.getOrderItems().size());
        assertEquals(2, created.getOrderItems().get(0).getQuantity());
    }

    @Test
    void deleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }
}
