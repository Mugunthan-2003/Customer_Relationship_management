package com.example.crm.entities;

import jakarta.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    // Getters and setters
    public Long getCustomerId() 
    { 
        return customerId; 
    }
    
    public void setCustomerId(Long customerId) 
    { 
        this.customerId = customerId; 
    }

    public String getName() 
    { 
        return name; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }

    public String getEmail() 
    { 
        return email; 
    }

    public void setEmail(String email) 
    { 
        this.email = email; 
    }

    public String getPhoneNumber() 
    { 
        return phoneNumber; 
    }

    public void setPhoneNumber(String phoneNumber) 
    { 
        this.phoneNumber = phoneNumber; 
    }

    public String getAddress() 
    { 
        return address; 
    }

    public void setAddress(String address) 
    { 
        this.address = address; 
    }

    @Override
    public String toString() 
    {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
