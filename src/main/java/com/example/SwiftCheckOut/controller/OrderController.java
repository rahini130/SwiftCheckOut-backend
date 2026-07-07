package com.example.SwiftCheckOut.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.SwiftCheckOut.entity.Order;
import com.example.SwiftCheckOut.service.OrderService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    
        public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

        @GetMapping("/getAll")
        @PreAuthorize("hasRole('CUSTOMER')")
        public ResponseEntity<List<Order>> getAllByCustomer() {

        return ResponseEntity.ok(orderService.getAll());
        }

        @PostMapping("/addOrder")
        public ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order) 
        {
        Order savedOrder = orderService.placeOrder(order);
         return ResponseEntity.ok(savedOrder);
        }


}
