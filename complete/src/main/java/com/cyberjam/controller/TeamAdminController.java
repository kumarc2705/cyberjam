package com.cyberjam.controller;
import java.util.ArrayList;
import java.util.List;
import com.cyberjam.model.Person;
import com.cyberjam.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team-admin")
public class TeamAdminController {

    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<Object> getAvailableApis() {
        String apis = """
            Available APIs:
            1. POST /team-admin/add-team - Add a new team
            2. GET /team-admin/get-teams - Get all teams
            3. GET /team-admin/get-team-info-with-id?id={id} - Get team info by ID
            4. PUT /team-admin/update-team-with-id?id={id} - Update team info by ID
            5. DELETE /team-admin/delete-team-with-id?id={id} - Delete team by ID
            6. POST /team-admin/add-member-to-team?id={id} - Add a member to a team
            7. DELETE /team-admin/remove-member-from-team?id={id}&memberName={memberName} - Remove a member from a team
            """;
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    @PostMapping("/add-team")
    public ResponseEntity<String> addTeam(@RequestBody Team newTeam) {
        try {
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants;

            // Check if the file exists and is not empty
            if (file.exists() && file.length() != 0) {
                constants = objectMapper.readValue(file, Map.class);
            } else {
                constants = new HashMap<>();
                constants.put("teams", new ArrayList<>());
            }

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            // Add the new team to the list
            teams.add(newTeam);

            // Update the constants map
            constants.put("teams", teams);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Team added successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-teams")
    public ResponseEntity<Object> getTeams() {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to read constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-team-info-with-id")
    public ResponseEntity<Object> getTeamInfoWithId(@RequestParam("id") String teamId) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            // Find the team by ID
            Optional<Team> team = teams.stream()
                    .filter(t -> teamId.equals(t.getId()))
                    .findFirst();

            if (team.isPresent()) {
                return new ResponseEntity<>(team.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to read constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-team-with-id")
    public ResponseEntity<String> updateTeamWithId(@RequestParam("id") String teamId, @RequestBody Team updatedTeam) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            // Find the team by ID and update it
            boolean teamUpdated = false;
            for (int i = 0; i < teams.size(); i++) {
                if (teamId.equals(teams.get(i).getId())) {
                    teams.set(i, updatedTeam);
                    teamUpdated = true;
                    break;
                }
            }

            if (!teamUpdated) {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }

            // Update the constants map
            constants.put("teams", teams);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Team updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-team-with-id")
    public ResponseEntity<String> deleteTeamWithId(@RequestParam("id") String teamId) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            // Remove the team by ID
            boolean removed = teams.removeIf(team -> teamId.equals(team.getId()));

            if (!removed) {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }

            // Update the constants map
            constants.put("teams", teams);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-member-to-team")
    public ResponseEntity<String> addMemberToTeam(@RequestParam("id") String teamId, @RequestBody Person newMember) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            // Find the team by ID and add the new member
            boolean memberAdded = false;
            for (Team team : teams) {
                if (teamId.equals(team.getId())) {
                    team.addMember(newMember);
                    memberAdded = true;
                    break;
                }
            }

            if (!memberAdded) {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }

            // Update the constants map
            constants.put("teams", teams);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Member added successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove-member-from-team")
    public ResponseEntity<String> removeMemberFromTeam(@RequestParam("id") String teamId, @RequestParam("memberName") String memberName) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Team> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Team>>() {});

            // Find the team by ID and remove the member
            boolean memberRemoved = false;
            for (Team team : teams) {
                if (teamId.equals(team.getId())) {
                    memberRemoved = team.removeMember(memberName);
                    break;
                }
            }

            if (!memberRemoved) {
                return new ResponseEntity<>("Team or member not found", HttpStatus.NOT_FOUND);
            }

            // Update the constants map
            constants.put("teams", teams);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Member removed successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}