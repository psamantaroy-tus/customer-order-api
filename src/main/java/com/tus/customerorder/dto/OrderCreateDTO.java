package com.tus.customerorder.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class OrderCreateDTO {
	/*

	//added validations for these fields
    @NotNull(message = "Order date is required")
    private String orderDate; // will convert to LocalDate in service

    @NotNull(message = "Amount is required")
    private Double amount;
*/	
	private LocalDate OrderDate;
	private double amount;
	
	public OrderCreateDTO() {}

	public LocalDate getOrderDate() {
		return OrderDate;
	}

	public void setOrderDte(LocalDate orderDate) {
		OrderDate = orderDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	};
	
   
}
