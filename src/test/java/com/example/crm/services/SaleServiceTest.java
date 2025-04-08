package com.example.crm.services;

import com.example.crm.entities.Sale;
import com.example.crm.repositories.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Date;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {

    @Mock
    private SaleRepository repository;

    @InjectMocks
    private SaleService service;

    private Sale sale;

    @BeforeEach
    void setup() {
        sale = new Sale();
        sale.setSaleId(1L);
        sale.setCustomer(null); 
        sale.setProduct(null); 
        sale.setDate(new Date());
        sale.setAmount(10.0);
        sale.setOrderStatus("Ordered");
    }

    @Test
    void testAddSale() {
        when(repository.save(any(Sale.class))).thenReturn(sale);
        Sale addedSale = service.addSale(sale);
        assertNotNull(addedSale);
        assertEquals(sale.getAmount(), addedSale.getAmount());
        verify(repository, times(1)).save(any(Sale.class));
    }

    @Test
    void testUpdateSale() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(sale));
        when(repository.save(any(Sale.class))).thenReturn(sale);
        Sale updatedSale = service.updateSale(sale);
        assertNotNull(updatedSale);
        assertEquals(sale.getAmount(), updatedSale.getAmount());
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any(Sale.class));
    }

    @Test
    void testDeleteSale() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteSale(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllSales() {
        List<Sale> sales = List.of(sale);
        when(repository.findAll()).thenReturn(sales);
        List<Sale> result = service.getAllSales();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetSaleById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(sale));
        Sale result = service.getSaleById(1L);
        assertNotNull(result);
        assertEquals(sale.getAmount(), result.getAmount());
        verify(repository, times(1)).findById(anyLong());
    }
}
