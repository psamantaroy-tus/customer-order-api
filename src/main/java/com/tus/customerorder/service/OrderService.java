package com.tus.customerorder.service;

import com.tus.customerorder.dto.OrderDTO;
import com.tus.customerorder.dto.OrderCreateDTO;
import com.tus.customerorder.entity.Customer;
import com.tus.customerorder.entity.Order;
import com.tus.customerorder.repository.CustomerRepository;
import com.tus.customerorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    // 1) Create order for a customer
    public OrderDTO createOrder(Long customerId, OrderCreateDTO dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setOrderDate(dto.getOrderDate());
        order.setAmount(dto.getAmount());
        order.setCustomer(customer);

        Order saved = orderRepository.save(order);
        return convertToDTO(saved);
    }

    // 2) Get all orders for a customer
    public List<OrderDTO> getOrdersByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return customer.getOrders()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper: convert Entity → DTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setAmount(order.getAmount());
        return dto;
    }
}
