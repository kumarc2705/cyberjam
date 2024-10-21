package com.cyberjam.service;

import com.cyberjam.model.TeamScoreView;
import com.cyberjam.model.JudgeScoreView;
import com.cyberjam.model.TeamRoleScore;
import com.cyberjam.model.TeamThemeScore;
import com.cyberjam.model.Judge;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ScoringAlgorithmService {

    private final JudgeService judgeService;

    public ScoringAlgorithmService(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    public double calculateRoleScore(TeamScoreView teamScoreView, String role) {
        double totalScore = 0.0;
        double totalWeight = 0.0;

        for (Map.Entry<String, JudgeScoreView> entry : teamScoreView.getJudgeScores().entrySet()) {
            JudgeScoreView judgeScore = entry.getValue();
            TeamRoleScore roleScore = judgeScore.getRoleScore();
            double score = getRoleScore(roleScore, role);

            // Fetch the judge object to get the weightage
            Judge judge = judgeService.getJudgeById(judgeScore.getJudgeId());
            double weight = getRoleWeight(judge, role);

            totalScore += score * weight;
            totalWeight += weight;
        }

        return totalWeight == 0 ? 0 : totalScore;
    }

    public double calculateThemeScore(TeamScoreView teamScoreView, String theme) {
        double totalScore = 0.0;
        double totalWeight = 0.0;

        for (Map.Entry<String, JudgeScoreView> entry : teamScoreView.getJudgeScores().entrySet()) {
            JudgeScoreView judgeScore = entry.getValue();
            TeamThemeScore themeScore = judgeScore.getThemeScore();
            double score = getThemeScore(themeScore, theme);

            // Fetch the judge object to get the weightage
            Judge judge = judgeService.getJudgeById(judgeScore.getJudgeId());
            double weight = getThemeWeight(judge, theme);

            totalScore += score * weight;
            totalWeight += weight;
        }

        return totalWeight == 0 ? 0 : totalScore;
    }

    public double calculateFinalScore(TeamScoreView teamScoreView) {
        double finalScore = 0.0;

        // Calculate role scores
        finalScore += calculateRoleScore(teamScoreView, "software");
        finalScore += calculateRoleScore(teamScoreView, "hardware");
        finalScore += calculateRoleScore(teamScoreView, "visualArt");
        finalScore += calculateRoleScore(teamScoreView, "music");
        finalScore += calculateRoleScore(teamScoreView, "wildCard");

        // Calculate theme scores
        finalScore += calculateThemeScore(teamScoreView, "ai");
        finalScore += calculateThemeScore(teamScoreView, "fashion");
        finalScore += calculateThemeScore(teamScoreView, "governance");
        finalScore += calculateThemeScore(teamScoreView, "sportsGaming");
        finalScore += calculateThemeScore(teamScoreView, "securityPrivacy");

        return finalScore;
    }

    public Map<String, Double> calculateRoleScores(TeamScoreView teamScoreView) {
        Map<String, Double> roleScores = new HashMap<>();
        roleScores.put("software", calculateRoleScore(teamScoreView, "software"));
        roleScores.put("hardware", calculateRoleScore(teamScoreView, "hardware"));
        roleScores.put("visualArt", calculateRoleScore(teamScoreView, "visualArt"));
        roleScores.put("music", calculateRoleScore(teamScoreView, "music"));
        roleScores.put("wildCard", calculateRoleScore(teamScoreView, "wildCard"));
        return roleScores;
    }

    public Map<String, Double> calculateThemeScores(TeamScoreView teamScoreView) {
        Map<String, Double> themeScores = new HashMap<>();
        themeScores.put("ai", calculateThemeScore(teamScoreView, "ai"));
        themeScores.put("fashion", calculateThemeScore(teamScoreView, "fashion"));
        themeScores.put("governance", calculateThemeScore(teamScoreView, "governance"));
        themeScores.put("sportsGaming", calculateThemeScore(teamScoreView, "sportsGaming"));
        themeScores.put("securityPrivacy", calculateThemeScore(teamScoreView, "securityPrivacy"));
        return themeScores;
    }

    public Map<String, Map<String, Double>> calculateAllScores(TeamScoreView teamScoreView) {
        Map<String, Map<String, Double>> allScores = new HashMap<>();
        allScores.put("roleScores", calculateRoleScores(teamScoreView));
        allScores.put("themeScores", calculateThemeScores(teamScoreView));
        return allScores;
    }

    private double getRoleScore(TeamRoleScore roleScore, String role) {
        switch (role) {
            case "software":
                return roleScore.getSoftwareScore();
            case "hardware":
                return roleScore.getHardwareScore();
            case "visualArt":
                return roleScore.getVisualArtScore();
            case "music":
                return roleScore.getMusicScore();
            case "wildCard":
                return roleScore.getWildCardScore();
            default:
                return 0.0;
        }
    }

    private double getRoleWeight(Judge judge, String role) {
        switch (role) {
            case "software":
                return judge.getRoleWeightage().getSoftwareWeight();
            case "hardware":
                return judge.getRoleWeightage().getHardwareWeight();
            case "visualArt":
                return judge.getRoleWeightage().getVisualArtWeight();
            case "music":
                return judge.getRoleWeightage().getMusicWeight();
            case "wildCard":
                return judge.getRoleWeightage().getWildCardWeight();
            default:
                return 0.0;
        }
    }

    private double getThemeScore(TeamThemeScore themeScore, String theme) {
        switch (theme) {
            case "ai":
                return themeScore.getAiScore();
            case "fashion":
                return themeScore.getFashionScore();
            case "governance":
                return themeScore.getGovernanceScore();
            case "sportsGaming":
                return themeScore.getSportsGamingScore();
            case "securityPrivacy":
                return themeScore.getSecurityPrivacyScore();
            default:
                return 0.0;
        }
    }

    private double getThemeWeight(Judge judge, String theme) {
        switch (theme) {
            case "ai":
                return judge.getThemeWeightage().getAiWeight();
            case "fashion":
                return judge.getThemeWeightage().getFashionWeight();
            case "governance":
                return judge.getThemeWeightage().getGovernanceWeight();
            case "sportsGaming":
                return judge.getThemeWeightage().getSportsGamingWeight();
            case "securityPrivacy":
                return judge.getThemeWeightage().getSecurityPrivacyWeight();
            default:
                return 0.0;
        }
    }
}