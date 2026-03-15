package com.tus.customerorder.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.tus.customerorder.entity.Customer;
import com.tus.customerorder.entity.Order;
import com.tus.customerorder.repository.CustomerRepository;
import com.tus.customerorder.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void createCustomer_shouldPersistAndReturnCustomer() {
        String email = "priyanka-" + UUID.randomUUID() + "@example.com";

        String requestBody = """
            {
              "name": "Priyanka",
              "email": "%s"
            }
            """.formatted(email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.postForEntity(
            "/customer/createcustomer",
            new HttpEntity<>(requestBody, headers),
            String.class
        );

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("\"name\":\"Priyanka\""));
        assertTrue(response.getBody().contains("\"id\":"));
        assertTrue(response.getBody().contains("\"createdAt\":"));

        assertTrue(
            customerRepository.findAll().stream()
                .anyMatch(savedCustomer -> email.equals(savedCustomer.getEmail()))
        );
    }

    @Test
    void getCustomer_shouldReturnExistingCustomer() {
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setEmail("alice-" + UUID.randomUUID() + "@example.com");
        customer.setCreatedAt(LocalDate.now());
        Customer saved = customerRepository.save(customer);

        ResponseEntity<String> response =
            restTemplate.getForEntity("/customer/" + saved.getId(), String.class);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("\"id\":" + saved.getId()));
        assertTrue(response.getBody().contains("\"name\":\"Alice\""));
    }

    @Test
    void deleteCustomer_shouldRemoveCustomer() {
        Customer customer = new Customer();
        customer.setName("Bob");
        customer.setEmail("bob-" + UUID.randomUUID() + "@example.com");
        customer.setCreatedAt(LocalDate.now());
        Customer saved = customerRepository.save(customer);

        restTemplate.delete("/customer/delete/" + saved.getId());

        assertFalse(customerRepository.existsById(saved.getId()));
    }
}