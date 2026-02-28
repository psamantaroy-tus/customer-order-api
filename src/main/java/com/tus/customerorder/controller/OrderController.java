package com.tus.customerorder.controller;

import com.tus.customerorder.dto.OrderDTO;
import com.tus.customerorder.dto.OrderCreateDTO;
import com.tus.customerorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    
    //3) Update order for a customer  
    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, 
                                @RequestBody OrderCreateDTO dto) {
        return orderService.updateOrder(orderId, dto);
    }
    
    // 4) Delete Order 
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
    
    //5) Get order by filtering date range
    @GetMapping("/filter")
    public List<OrderDTO> getOrdersByDate(
            @RequestParam("start") @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate end) {
        
        // filtering by date range
        return orderService.getOrdersByDateRange(start, end);
    }
}
