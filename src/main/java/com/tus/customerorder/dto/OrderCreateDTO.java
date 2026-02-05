package com.tus.customerorder.dto;

import jakarta.validation.constraints.NotNull;

public class OrderCreateDTO {

	//added validations for these fields
    @NotNull(message = "Order date is required")
    private String orderDate; // will convert to LocalDate in service

    @NotNull(message = "Amount is required")
    private Double amount;

   
}
