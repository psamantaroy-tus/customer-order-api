package com.tus.customerorder.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.customerorder.entity.Order;


//this is an interface that extends the jparepository. Jpa gives you the CURD operation and all the requiremnts for the controllers
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	//Implementing date range filter for order service
	//This method will query to database for orders within date range
	List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}

