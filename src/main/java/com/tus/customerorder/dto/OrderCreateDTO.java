package com.tus.customerorder.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public class OrderCreateDTO {
	
	//validate date inputs using jakarta annotation to ensure date is not null
    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Amount is required")   
    private Double amount;
	
	public OrderCreateDTO() {}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	};
	
	  
}
