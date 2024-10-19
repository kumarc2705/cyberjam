package com.cyberjam.service;

import com.cyberjam.model.Judge;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class JudgeService {

    private static final String CONSTANTS_FILE_PATH = "src/main/resources/constants.json";
    private final Map<String, Judge> judges = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JudgeService() {
        loadJudgesFromFile();
    }

    private void loadJudgesFromFile() {
        try {
            // Read the existing data from the constants file
            File file = new File(CONSTANTS_FILE_PATH);
            Map<String, Object> constants = objectMapper.readValue(file, Map.class);

            // Deserialize the list of judges
            List<Judge> judgeList = objectMapper.convertValue(constants.get("judges"), new TypeReference<List<Judge>>() {});

            // Populate the judges map
            for (Judge judge : judgeList) {
                judges.put(judge.getJudgeId(), judge);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Judge getJudgeById(String judgeId) {
        return judges.get(judgeId);
    }
}