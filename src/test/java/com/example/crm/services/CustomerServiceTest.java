package com.example.crm.services;

import com.example.crm.entities.Customer;
import com.example.crm.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Mugunthan");
        customer.setEmail("mugunthan@gmail.com");
        customer.setPhoneNumber("9791674390");
        customer.setAddress("Chennai");
    }

    @Test
    void testAddCustomer() {
        when(repository.save(any(Customer.class))).thenReturn(customer);
        Customer addedCustomer = service.addCustomer(customer);
        assertNotNull(addedCustomer);
        assertEquals(customer.getName(), addedCustomer.getName());
        verify(repository, times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomer() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);
        Customer updatedCustomer = service.updateCustomer(customer);
        assertNotNull(updatedCustomer);
        assertEquals(customer.getName(), updatedCustomer.getName());
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteCustomer(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = List.of(customer);
        when(repository.findAll()).thenReturn(customers);
        List<Customer> result = service.getAllCustomers();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        Customer result = service.getCustomerById(1L);
        assertNotNull(result);
        assertEquals(customer.getName(), result.getName());
        verify(repository, times(1)).findById(anyLong());
    }
}
