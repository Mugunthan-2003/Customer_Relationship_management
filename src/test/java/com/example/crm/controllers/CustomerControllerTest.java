package com.example.crm.controllers;

import com.example.crm.entities.Customer;
import com.example.crm.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    private MockMvc mockMvc;

    private Customer customer;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Mugunthan");
        customer.setEmail("mugunthan@gmail.com");
        customer.setPhoneNumber("9791674390");
        customer.setAddress("Chennai");
    }

    @Test
    void testAddCustomer() throws Exception {
        when(service.addCustomer(any(Customer.class))).thenReturn(customer);
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk());
        verify(service, times(1)).addCustomer(any(Customer.class));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        when(service.updateCustomer(any(Customer.class))).thenReturn(customer);
        mockMvc.perform(put("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk());
        verify(service, times(1)).updateCustomer(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteCustomer(anyLong());
    }

    @Test
    void testGetAllCustomers() throws Exception {
        List<Customer> customers = List.of(customer);
        when(service.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
        verify(service, times(1)).getAllCustomers();
    }

    @Test
    void testGetCustomerById() throws Exception {
        when(service.getCustomerById(anyLong())).thenReturn(customer);
        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).getCustomerById(anyLong());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
