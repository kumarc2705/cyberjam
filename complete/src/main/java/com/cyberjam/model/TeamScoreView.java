package com.cyberjam.model;

import java.util.HashMap;
import java.util.Map;

public class TeamScoreView {
    private String teamId;
    private Map<String, JudgeScoreView> judgeScores;

    // Default constructor
    public TeamScoreView() {
        this.judgeScores = new HashMap<>();
    }

    // Parameterized constructor
    public TeamScoreView(String teamId, Map<String, JudgeScoreView> judgeScores) {
        this.teamId = teamId;
        this.judgeScores = judgeScores;
    }

    // Getters and Setters
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Map<String, JudgeScoreView> getJudgeScores() {
        return judgeScores;
    }

    public void setJudgeScores(Map<String, JudgeScoreView> judgeScores) {
        this.judgeScores = judgeScores;
    }

        // Method to add a judge score
        public void addJudgeScore(JudgeScoreView judgeScore) {
          String judgeId = judgeScore.getJudgeId();
          if (judgeId != null && !this.judgeScores.containsKey(judgeId)) {
              this.judgeScores.put(judgeId, judgeScore);
          }
      }
  
      // Method to remove a judge score
      public boolean removeJudgeScore(String judgeId) {
          return this.judgeScores.remove(judgeId) != null;
      }
}