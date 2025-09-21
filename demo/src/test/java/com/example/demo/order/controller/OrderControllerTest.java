package com.example.demo.order.controller;

import com.example.demo.order.dto.OrderItemRequest;
import com.example.demo.order.dto.OrderRequest;
import com.example.demo.order.entity.Order;
import com.example.demo.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(new Order()));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById() throws Exception {
        Order order = new Order();
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(1L);
        OrderItemRequest itemRequest = new OrderItemRequest();
        itemRequest.setProductId(1L);
        itemRequest.setQuantity(1);
        orderRequest.setItems(List.of(itemRequest));

        when(orderService.createOrder(any(OrderRequest.class))).thenReturn(new Order());

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());
    }
}
