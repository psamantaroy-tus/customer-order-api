package com.tus.customerorder.dto;

import java.time.LocalDate;
import java.util.List;

//This is what return from customer
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDate createdAt;
    private List<OrderDTO> orders;

   
}

