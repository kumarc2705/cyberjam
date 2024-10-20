package com.cyberjam.service;

import com.cyberjam.model.Participant;
import com.cyberjam.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamService {
    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private Map<String, Team> teams = new HashMap<>();
    private List<Participant> participants;
    private ObjectMapper objectMapper = new ObjectMapper();

    public TeamService() {
        loadParticipants();
        loadTeams();
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
                List<Team> teamList = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});
                if (teamList != null) {
                    for (Team team : teamList) {
                        teams.put(team.getId(), team);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addMemberToTeam(String teamId, Participant participant) {
        if (participants.stream().noneMatch(p -> p.getParticipantId().equals(participant.getParticipantId()))) {
            return false; // Participant not found in the participants list
        }

        Team team = teams.get(teamId);
        if (team != null) {
            team.addMember(participant);
            saveTeams();
            return true;
        }
        return false;
    }

    private void saveTeams() {
        try {
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);
            constants.put("teams", new ArrayList<>(teams.values()));
            objectMapper.writeValue(file, constants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}