package com.example.crm.services;

import com.example.crm.entities.Sale;
import com.example.crm.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Sale addSale(Sale sale) {
        return repository.save(sale);
    }

    public Sale updateSale(Sale sale) {
        Sale existingSale = repository.findById(sale.getSaleId()).orElse(null);
        if (existingSale != null) {
            existingSale.setCustomer(sale.getCustomer());
            existingSale.setProduct(sale.getProduct());
            existingSale.setDate(sale.getDate());
            existingSale.setAmount(sale.getAmount());
            existingSale.setOrderStatus(sale.getOrderStatus());
            return repository.save(existingSale);
        } else {
            return null;
        }
    }

    public void deleteSale(Long saleId) {
        repository.deleteById(saleId);
    }

    public List<Sale> getAllSales() {
        return repository.findAll();
    }

    public Sale getSaleById(Long saleId) {
        return repository.findById(saleId).orElse(null);
    }
}
