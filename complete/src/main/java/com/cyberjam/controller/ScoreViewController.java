package com.cyberjam.controller;

import com.cyberjam.model.TeamRoleScore;
import com.cyberjam.model.TeamThemeScore;
import com.cyberjam.model.TeamScoreView;
import com.cyberjam.model.JudgeScoreView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/score-view")
public class ScoreViewController {

    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<Object> getAvailableApis() {
        String apis = """
            Available APIs:
            1. POST /score-view/assign-scores - Assign scores to a team
            2. PUT /score-view/update-role-score?teamId={teamId}&judgeId={judgeId} - Update role score for a team by a judge
            3. PUT /score-view/update-theme-score?teamId={teamId}&judgeId={judgeId} - Update theme score for a team by a judge
            """;
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    @PostMapping("/assign-scores")
    public ResponseEntity<String> assignScores(@RequestBody TeamScoreView newScore) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Map<String, Object>> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Map<String, Object>>>() {});

            // Check if the team exists
            boolean teamExists = teams.stream().anyMatch(team -> newScore.getTeamId().equals(team.get("id")));
            if (!teamExists) {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }

            // Deserialize the list of team scores
            List<TeamScoreView> teamScores = objectMapper.convertValue(constants.get("teamScores"), new TypeReference<List<TeamScoreView>>() {});

            // Initialize the teamScores list if it is null
            if (teamScores == null) {
                teamScores = new ArrayList<>();
            }

            // Add the new score to the list
            teamScores.add(newScore);

            // Update the constants map
            constants.put("teamScores", teamScores);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Scores assigned successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-role-score")
    public ResponseEntity<String> updateRoleScore(@RequestParam("teamId") String teamId, @RequestParam("judgeId") String judgeId, @RequestBody TeamRoleScore newRoleScore) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Map<String, Object>> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Map<String, Object>>>() {});

            // Check if the team exists
            boolean teamExists = teams.stream().anyMatch(team -> teamId.equals(team.get("id")));
            if (!teamExists) {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }

            // Deserialize the list of team scores
            List<TeamScoreView> teamScores = objectMapper.convertValue(constants.get("teamScores"), new TypeReference<List<TeamScoreView>>() {});

            // Initialize the teamScores list if it is null
            if (teamScores == null) {
                return new ResponseEntity<>("No team scores found", HttpStatus.NOT_FOUND);
            }

            // Find the team score by teamId and update the role score for the specified judge
            boolean scoreUpdated = false;
            for (TeamScoreView teamScore : teamScores) {
                if (teamId.equals(teamScore.getTeamId())) {
                    JudgeScoreView judgeScore = teamScore.getJudgeScores().get(judgeId);
                    if (judgeScore != null) {
                        judgeScore.setRoleScore(newRoleScore);
                        scoreUpdated = true;
                        break;
                    }
                }
            }

            if (!scoreUpdated) {
                return new ResponseEntity<>("Team or Judge not found", HttpStatus.NOT_FOUND);
            }

            // Update the constants map
            constants.put("teamScores", teamScores);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Role score updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-theme-score")
    public ResponseEntity<String> updateThemeScore(@RequestParam("teamId") String teamId, @RequestParam("judgeId") String judgeId, @RequestBody TeamThemeScore newThemeScore) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of teams
            List<Map<String, Object>> teams = objectMapper.convertValue(constants.get("teams"), new TypeReference<List<Map<String, Object>>>() {});

            // Check if the team exists
            boolean teamExists = teams.stream().anyMatch(team -> teamId.equals(team.get("id")));
            if (!teamExists) {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }

            // Deserialize the list of team scores
            List<TeamScoreView> teamScores = objectMapper.convertValue(constants.get("teamScores"), new TypeReference<List<TeamScoreView>>() {});

            // Initialize the teamScores list if it is null
            if (teamScores == null) {
                return new ResponseEntity<>("No team scores found", HttpStatus.NOT_FOUND);
            }

            // Find the team score by teamId and update the theme score for the specified judge
            boolean scoreUpdated = false;
            for (TeamScoreView teamScore : teamScores) {
                if (teamId.equals(teamScore.getTeamId())) {
                    JudgeScoreView judgeScore = teamScore.getJudgeScores().get(judgeId);
                    if (judgeScore != null) {
                        judgeScore.setThemeScore(newThemeScore);
                        scoreUpdated = true;
                        break;
                    }
                }
            }

            if (!scoreUpdated) {
                return new ResponseEntity<>("Team or Judge not found", HttpStatus.NOT_FOUND);
            }

            // Update the constants map
            constants.put("teamScores", teamScores);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Theme score updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}