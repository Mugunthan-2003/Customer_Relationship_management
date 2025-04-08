package com.example.crm.controllers;

import com.example.crm.entities.Interaction;
import com.example.crm.services.InteractionService;
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
public class InteractionControllerTest {

    @Mock
    private InteractionService service;

    @InjectMocks
    private InteractionController controller;

    private MockMvc mockMvc;

    private Interaction interaction;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        interaction = new Interaction();
        interaction.setInteractionId(1L);
        interaction.setCustomer(null); 
        interaction.setInteractionType("Phone Call");
        interaction.setInteractionDate(new Date());
        interaction.setNotes("Query on product availability");
    }

    @Test
    void testAddInteraction() throws Exception {
        when(service.addInteraction(any(Interaction.class))).thenReturn(interaction);
        mockMvc.perform(post("/api/interactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(interaction)))
                .andExpect(status().isOk());
        verify(service, times(1)).addInteraction(any(Interaction.class));
    }

    @Test
    void testUpdateInteraction() throws Exception {
        when(service.updateInteraction(any(Interaction.class))).thenReturn(interaction);
        mockMvc.perform(put("/api/interactions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(interaction)))
                .andExpect(status().isOk());
        verify(service, times(1)).updateInteraction(any(Interaction.class));
    }

    @Test
    void testDeleteInteraction() throws Exception {
        mockMvc.perform(delete("/api/interactions/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteInteraction(anyLong());
    }

    @Test
    void testGetAllInteractions() throws Exception {
        List<Interaction> interactions = List.of(interaction);
        when(service.getAllInteractions()).thenReturn(interactions);
        mockMvc.perform(get("/api/interactions"))
                .andExpect(status().isOk());
        verify(service, times(1)).getAllInteractions();
    }

    @Test
    void testGetInteractionById() throws Exception {
        when(service.getInteractionById(anyLong())).thenReturn(interaction);
        mockMvc.perform(get("/api/interactions/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).getInteractionById(anyLong());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
