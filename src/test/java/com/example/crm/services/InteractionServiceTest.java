package com.example.crm.services;

import com.example.crm.entities.Interaction;
import com.example.crm.repositories.InteractionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InteractionServiceTest {

    @Mock
    private InteractionRepository repository;

    @InjectMocks
    private InteractionService service;

    private Interaction interaction;

    @BeforeEach
    void setup() {
        interaction = new Interaction();
        interaction.setInteractionId(1L);
        interaction.setCustomer(null); 
        interaction.setInteractionType("Phone Call");
        interaction.setInteractionDate(new Date());
        interaction.setNotes("Query on product availability");
    }

    @Test
    void testAddInteraction() {
        when(repository.save(any(Interaction.class))).thenReturn(interaction);
        Interaction addedInteraction = service.addInteraction(interaction);
        assertNotNull(addedInteraction);
        assertEquals(interaction.getInteractionType(), addedInteraction.getInteractionType());
        verify(repository, times(1)).save(any(Interaction.class));
    }

    @Test
    void testUpdateInteraction() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(interaction));
        when(repository.save(any(Interaction.class))).thenReturn(interaction);
        Interaction updatedInteraction = service.updateInteraction(interaction);
        assertNotNull(updatedInteraction);
        assertEquals(interaction.getInteractionType(), updatedInteraction.getInteractionType());
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any(Interaction.class));
    }

    @Test
    void testDeleteInteraction() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteInteraction(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllInteractions() {
        List<Interaction> interactions = List.of(interaction);
        when(repository.findAll()).thenReturn(interactions);
        List<Interaction> result = service.getAllInteractions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetInteractionById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(interaction));
        Interaction result = service.getInteractionById(1L);
        assertNotNull(result);
        assertEquals(interaction.getInteractionType(), result.getInteractionType());
        verify(repository, times(1)).findById(anyLong());
    }
}
