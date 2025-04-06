package com.example.crm.controllers;

import java.util.List;
import com.example.crm.entities.Sale;
import com.example.crm.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    public Sale addSale(@RequestBody Sale sale) {
        return service.addSale(sale);
    }

    @PutMapping("/{saleId}")
    public Sale updateSale(@PathVariable Long saleId, @RequestBody Sale sale) {
        sale.setSaleId(saleId);
        return service.updateSale(sale);
    }

    @DeleteMapping("/{saleId}")
    public void deleteSale(@PathVariable Long saleId) {
        service.deleteSale(saleId);
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return service.getAllSales();
    }

    @GetMapping("/{saleId}")
    public Sale getSaleById(@PathVariable Long saleId) {
        return service.getSaleById(saleId);
    }
}
