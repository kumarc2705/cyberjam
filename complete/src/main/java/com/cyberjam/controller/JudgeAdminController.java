package com.cyberjam.controller;

import com.cyberjam.model.Judge;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;;
@RestController
@RequestMapping("/judge-admin")
public class JudgeAdminController {

    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/add-judge")
    public ResponseEntity<String> addJudge(@RequestBody Judge newJudge) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);
            System.out.println(newJudge.getRoleWeightage().getSoftwareWeight());
            // Deserialize the list of judges
            List<Judge> judges = objectMapper.convertValue(constants.get("judges"), new TypeReference<List<Judge>>() {});

        // Check if a judge with the same judgeId already exists
        boolean judgeExists = judges.stream()
        .anyMatch(judge -> judge.getJudgeId().equals(newJudge.getJudgeId()));

        if (judgeExists) {
            return new ResponseEntity<>("Judge with this ID already exists", HttpStatus.CONFLICT);
        }
            // Add the new judge to the list
            judges.add(newJudge);

            // Update the constants map
            constants.put("judges", judges);

            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);

            return new ResponseEntity<>("Judge added successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getAvailableMethods() {
        Map<String, String> methods = new HashMap<>();
        methods.put("GET /judge-admin", "Get all available API methods");
        methods.put("GET /judges", "Get all judges");
        methods.put("POST /add-judge", "Add a new judge");
        methods.put("DELETE /remove-judge", "Remove a judge by judgeId");
        // Add other API methods as needed

        return new ResponseEntity<>(methods, HttpStatus.OK);
    }


    @GetMapping("/judges")
    public ResponseEntity<Object> getJudges(@RequestParam(value = "judge", required = false) String judgeName) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of judges
            List<Judge> judges = objectMapper.convertValue(constants.get("judges"), new TypeReference<List<Judge>>() {});

            // Filter judges if judgeName is provided
            if (judgeName != null && !judgeName.isEmpty()) {
                judges = judges.stream()
                        .filter(judge -> judgeName.equals(judge.getName()))
                        .collect(Collectors.toList());
            }

            return new ResponseEntity<>(judges, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to read constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("/update-judge/{judgeId}")
    public ResponseEntity<String> updateJudge(@PathVariable String judgeId, @RequestBody Judge updatedJudge) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);
            // Deserialize the list of judges
            List<Judge> judges = objectMapper.convertValue(constants.get("judges"), new TypeReference<List<Judge>>() {});
    
            // Find and update the judge
            boolean updated = false;
            for (int i = 0; i < judges.size(); i++) {
                if (judgeId.equals(judges.get(i).getJudgeId())) {
                    // Preserve the original judgeId
                    updatedJudge.setJudgeId(judgeId);
                    judges.set(i, updatedJudge);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                return new ResponseEntity<>("Judge not found", HttpStatus.NOT_FOUND);
            }
    
            // Update the constants map
            constants.put("judges", judges);
    
            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);
    
            return new ResponseEntity<>("Judge updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove-judge/{judgeId}")
    public ResponseEntity<String> deleteJudge(@PathVariable String judgeId) {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);
    
            // Deserialize the list of judges
            List<Judge> judges = objectMapper.convertValue(constants.get("judges"), new TypeReference<List<Judge>>() {});
    
            // Remove the judge by ID
            boolean removed = judges.removeIf(judge -> judgeId.equals(judge.getJudgeId()));
    
            if (!removed) {
                return new ResponseEntity<>("Judge not found", HttpStatus.NOT_FOUND);
            }
    
            // Deserialize the list of team scores
            List<Map<String, Object>> teamScores = objectMapper.convertValue(constants.get("teamScores"), new TypeReference<List<Map<String, Object>>>() {});
    
            // Remove the scores associated with the judge
            for (Map<String, Object> teamScore : teamScores) {
                Map<String, Object> judgeScores = (Map<String, Object>) teamScore.get("judgeScores");
                if (judgeScores != null) {
                    judgeScores.remove(judgeId);
                }
            }
    
            // Update the constants map
            constants.put("judges", judges);
            constants.put("teamScores", teamScores);
    
            // Write the updated list back to the constants file
            objectMapper.writeValue(file, constants);
    
            return new ResponseEntity<>("Judge and associated scores deleted successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update constants file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}