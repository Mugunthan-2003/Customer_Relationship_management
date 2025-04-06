package com.example.crm.services;

import com.example.crm.entities.Interaction;
import com.example.crm.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository repository;

    public Interaction addInteraction(Interaction interaction) {
        return repository.save(interaction);
    }

    public Interaction updateInteraction(Interaction interaction) {
        Interaction existingInteraction = repository.findById(interaction.getInteractionId()).orElse(null);
        if (existingInteraction != null) {
            existingInteraction.setCustomer(interaction.getCustomer());
            existingInteraction.setInteractionType(interaction.getInteractionType());
            existingInteraction.setInteractionDate(interaction.getInteractionDate());
            existingInteraction.setNotes(interaction.getNotes());
            return repository.save(existingInteraction);
        } else {
            return null;
        }
    }

    public void deleteInteraction(Long interactionId) {
        repository.deleteById(interactionId);
    }

    public List<Interaction> getAllInteractions() {
        return repository.findAll();
    }

    public Interaction getInteractionById(Long interactionId) {
        return repository.findById(interactionId).orElse(null);
    }
}
