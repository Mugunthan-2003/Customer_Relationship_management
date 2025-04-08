package com.example.crm.controllers;

import com.example.crm.entities.Sale;
import com.example.crm.services.SaleService;
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
public class SaleControllerTest {

    @Mock
    private SaleService service;

    @InjectMocks
    private SaleController controller;

    private MockMvc mockMvc;

    private Sale sale;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        sale = new Sale();
        sale.setSaleId(1L);
        sale.setCustomer(null); 
        sale.setProduct(null); 
        sale.setDate(new Date());
        sale.setAmount(10.0);
        sale.setOrderStatus("Ordered");
    }

    @Test
    void testAddSale() throws Exception {
        when(service.addSale(any(Sale.class))).thenReturn(sale);
        mockMvc.perform(post("/api/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sale)))
                .andExpect(status().isOk());
        verify(service, times(1)).addSale(any(Sale.class));
    }

    @Test
    void testUpdateSale() throws Exception {
        when(service.updateSale(any(Sale.class))).thenReturn(sale);
        mockMvc.perform(put("/api/sales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sale)))
                .andExpect(status().isOk());
        verify(service, times(1)).updateSale(any(Sale.class));
    }

    @Test
    void testDeleteSale() throws Exception {
        mockMvc.perform(delete("/api/sales/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteSale(anyLong());
    }

    @Test
    void testGetAllSales() throws Exception {
        List<Sale> sales = List.of(sale);
        when(service.getAllSales()).thenReturn(sales);
        mockMvc.perform(get("/api/sales"))
                .andExpect(status().isOk());
        verify(service, times(1)).getAllSales();
    }

    @Test
    void testGetSaleById() throws Exception {
        when(service.getSaleById(anyLong())).thenReturn(sale);
        mockMvc.perform(get("/api/sales/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).getSaleById(anyLong());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
