package com.cyberjam.controller;

import com.cyberjam.model.TeamScoreView;
import com.cyberjam.service.ScoringAlgorithmService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/results")
public class ResultsController {

    @Autowired
    private ScoringAlgorithmService scoringAlgorithmService;

    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{teamName}/totalScore")
    public ResponseEntity<Double> getTotalScore(@PathVariable("teamName") String teamName) {
        TeamScoreView teamScoreView = getTeamScoreViewByName(teamName);
        if (teamScoreView == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double totalScore = scoringAlgorithmService.calculateFinalScore(teamScoreView);
        return new ResponseEntity<>(totalScore, HttpStatus.OK);
    }

    @GetMapping("/{teamName}/role-scores")
    public ResponseEntity<Map<String, Double>> getRoleScores(@PathVariable("teamName") String teamName) {
        TeamScoreView teamScoreView = getTeamScoreViewByName(teamName);
        if (teamScoreView == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, Double> roleScores = scoringAlgorithmService.calculateRoleScores(teamScoreView);
        return new ResponseEntity<>(roleScores, HttpStatus.OK);
    }

    @GetMapping("/{teamName}/theme-scores")
    public ResponseEntity<Map<String, Double>> getThemeScores(@PathVariable("teamName") String teamName) {
        TeamScoreView teamScoreView = getTeamScoreViewByName(teamName);
        if (teamScoreView == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, Double> themeScores = scoringAlgorithmService.calculateThemeScores(teamScoreView);
        return new ResponseEntity<>(themeScores, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{teamName}/all-scores")
    public ResponseEntity<Map<String, Object>> getAllScores(@PathVariable("teamName") String teamName) {
        TeamScoreView teamScoreView = getTeamScoreViewByName(teamName);
        if (teamScoreView == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        double totalScore = scoringAlgorithmService.calculateFinalScore(teamScoreView);
        Map<String, Double> roleScores = scoringAlgorithmService.calculateRoleScores(teamScoreView);
        Map<String, Double> themeScores = scoringAlgorithmService.calculateThemeScores(teamScoreView);

        Map<String, Object> allScores = new HashMap<>();
        allScores.put("totalScore", totalScore);
        allScores.put("roleScores", roleScores);
        allScores.put("themeScores", themeScores);

        return new ResponseEntity<>(allScores, HttpStatus.OK);
    }


    private TeamScoreView getTeamScoreViewByName(String teamName) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of team scores
            List<TeamScoreView> teamScores = objectMapper.convertValue(constants.get("teamScores"), new TypeReference<List<TeamScoreView>>() {});

            // Find the team score view by team name
            for (TeamScoreView teamScoreView : teamScores) {
                if (teamScoreView.getTeamId().equals(teamName)) {
                    return teamScoreView;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}