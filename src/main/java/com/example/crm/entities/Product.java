package com.example.crm.entities;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productCategory;
    private Double price;

    // Getters and setters
    public Long getProductId() 
    { 
        return productId; 
    }
    
    public void setProductId(Long productId) 
    { 
        this.productId = productId; 
    }
    
    public String getProductName() 
    { 
        return productName; 
    }
    
    public void setProductName(String productName) 
    { 
        this.productName = productName; 
    }
    
    public String getProductCategory() 
    { 
        return productCategory; 
    }
    
    public void setProductCategory(String productCategory) 
    { 
        this.productCategory = productCategory; 
    }
    
    public Double getPrice() 
    { 
        return price; 
    }
    
    public void setPrice(Double price) 
    { 
        this.price = price; 
    }

    @Override
    public String toString() 
    {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", price=" + price +
                '}';
    }
}
