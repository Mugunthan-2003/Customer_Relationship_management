package com.example.crm.entities;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Date date;
    private Double amount;
    private String orderStatus;

    // Getters and setters
    public Long getSaleId() 
    { 
        return saleId; 
    }
    
    public void setSaleId(Long saleId) 
    { 
        this.saleId = saleId; 
    }
    
    public Customer getCustomer() 
    { 
        return customer; 
    }
    
    public void setCustomer(Customer customer) 
    { 
        this.customer = customer; 
    }
    
    public Product getProduct() 
    { 
        return product; 
    }
    
    public void setProduct(Product product) 
    { 
        this.product = product; 
    }
    
    public Date getDate() 
    { 
        return date; 
    }
    
    public void setDate(Date date) 
    { 
        this.date = date; 
    }
    
    public Double getAmount() 
    { 
        return amount; 
    }
    
    public void setAmount(Double amount) 
    { 
        this.amount = amount; 
    }
    
    public String getOrderStatus() 
    { 
        return orderStatus; 
    }
    
    public void setOrderStatus(String orderStatus) 
    { 
        this.orderStatus = orderStatus; 
    }

    @Override
    public String toString() 
    {
        return "Sale {" +
                "saleId=" + saleId +
                ", customer=" + customer +
                ", product=" + product +
                ", date=" + date +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
