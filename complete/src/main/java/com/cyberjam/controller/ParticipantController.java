package com.cyberjam.controller;
import com.cyberjam.model.Participant;
import com.cyberjam.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/participant-admin")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping("/add-participant")
    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant) {
        Participant addedParticipant = participantService.addParticipant(participant);
        return new ResponseEntity<>(addedParticipant, HttpStatus.CREATED);
    }

    @GetMapping("/participants")
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @PutMapping("/update-participant/{participantId}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable String participantId, @RequestBody Participant updatedParticipant) {
        Participant participant = participantService.updateParticipant(participantId, updatedParticipant);
        if (participant != null) {
            return new ResponseEntity<>(participant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/remove-participant/{participantId}")
    public ResponseEntity<String> removeParticipant(@PathVariable String participantId) {
        boolean removed = participantService.removeParticipant(participantId);
        if (removed) {
            return new ResponseEntity<>("Participant removed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Participant not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getAvailableMethods() {
        Map<String, String> methods = new HashMap<>();
        methods.put("POST /participants-admin/add-participant", "Add a new participant");
        methods.put("GET /participants-admin/participants", "Get all participants");
        methods.put("DELETE /participants-admin/remove-participant", "Remove a participant by ID");
        return new ResponseEntity<>(methods, HttpStatus.OK);
    }
}