package com.example.crm.controllers;

import java.util.List;
import com.example.crm.entities.Interaction;
import com.example.crm.services.InteractionService;
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
@RequestMapping("/api/interactions")
public class InteractionController {

    @Autowired
    private InteractionService service;

    @PostMapping
    public Interaction addInteraction(@RequestBody Interaction interaction) {
        return service.addInteraction(interaction);
    }

    @PutMapping("/{interactionId}")
    public Interaction updateInteraction(@PathVariable Long interactionId, @RequestBody Interaction interaction) {
        interaction.setInteractionId(interactionId);
        return service.updateInteraction(interaction);
    }

    @DeleteMapping("/{interactionId}")
    public void deleteInteraction(@PathVariable Long interactionId) {
        service.deleteInteraction(interactionId);
    }

    @GetMapping
    public List<Interaction> getAllInteractions() {
        return service.getAllInteractions();
    }

    @GetMapping("/{interactionId}")
    public Interaction getInteractionById(@PathVariable Long interactionId) {
        return service.getInteractionById(interactionId);
    }
}
