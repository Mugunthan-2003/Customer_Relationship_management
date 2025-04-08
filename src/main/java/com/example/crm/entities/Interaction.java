package com.example.crm.entities;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;*/
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "interactions")
public class Interaction 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String interactionType;
    private Date interactionDate;
    private String notes;

    // Getters and setters
    public Long getInteractionId() 
    { 
        return interactionId; 
    }

    public void setInteractionId(Long interactionId) 
    { 
        this.interactionId = interactionId; 
    }

    public Customer getCustomer() 
    { 
        return customer; 
    }

    public void setCustomer(Customer customer) 
    { 
        this.customer = customer; 
    }

    public String getInteractionType() 
    { 
        return interactionType; 
    }

    public void setInteractionType(String interactionType) 
    { 
        this.interactionType = interactionType; 
    }

    public Date getInteractionDate() 
    { 
        return interactionDate; 
    }

    public void setInteractionDate(Date interactionDate) 
    { 
        this.interactionDate = interactionDate; 
    }

    public String getNotes() 
    { 
        return notes; 
    }

    public void setNotes(String notes) 
    { 
        this.notes = notes; 
    }

    @Override
    public String toString() 
    {
        return "Interaction{" +
                "interactionId=" + interactionId +
                ", customer=" + customer +
                ", interactionType='" + interactionType + '\'' +
                ", interactionDate=" + interactionDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
