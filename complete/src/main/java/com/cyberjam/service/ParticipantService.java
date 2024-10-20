package com.cyberjam.service;

import com.cyberjam.model.Participant;
import com.cyberjam.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ParticipantService {
    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private List<Participant> participants = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    public ParticipantService() {
        loadParticipants();
        loadTeams();
    }

    public Participant addParticipant(Participant participant) {
        boolean exists = participants.stream()
                .anyMatch(p -> p.getParticipantId().equals(participant.getParticipantId()));
        if (!exists) {
            participants.add(participant);
            saveParticipants();
            return participant;
        }
        return null; // Return null or throw an exception if the participant already exists
    }

    public List<Participant> getAllParticipants() {
        return participants;
    }

    private void loadParticipants() {
        try {
            File file = new File(CONSTANTS_FILE_PATH);
            if (file.exists()) {
                Map<String, Object> constants = objectMapper.readValue(file, Map.class);
                participants = objectMapper.convertValue(constants.get("participants"), new TypeReference<List<Participant>>() {});
                if (participants == null) {
                    participants = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTeams() {
        try {
            File file = new File(CONSTANTS_FILE_PATH);
            if (file.exists()) {
                Map<String, Object> constants = objectMapper.readValue(file, Map.class);
                teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});
                if (teams == null) {
                    teams = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Participant updateParticipant(String participantId, Participant updatedParticipant) {
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getParticipantId().equals(participantId)) {
                participants.set(i, updatedParticipant);
                saveParticipants();
                return updatedParticipant;
            }
        }
        return null;
    }

    public boolean removeParticipant(String participantId) {
        boolean removed = participants.removeIf(p -> p.getParticipantId().equals(participantId));
        if (removed) {
            for (Team team : teams) {
                team.getMembers().removeIf(member -> member.getParticipantId().equals(participantId));
            }
            saveParticipants();
            saveTeams();
        }
        return removed;
    }

    private void saveParticipants() {
        try {
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);
            constants.put("participants", participants);
            objectMapper.writeValue(file, constants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTeams() {
        try {
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);
            constants.put("teams", teams);
            objectMapper.writeValue(file, constants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}