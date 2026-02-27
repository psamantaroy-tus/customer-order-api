package com.tus.customerorder.controller;

import com.tus.customerorder.dto.OrderDTO;
import com.tus.customerorder.dto.OrderCreateDTO;
import com.tus.customerorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1) Create order for a customer
    @PostMapping("/create/{customerId}")
    public OrderDTO createOrder(@PathVariable Long customerId,
                                @RequestBody OrderCreateDTO dto) {
        return orderService.createOrder(customerId, dto);
    }

    // 2) Get all orders for a customer
    @GetMapping("/customer/{customerId}")
    public List<OrderDTO> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }
}
