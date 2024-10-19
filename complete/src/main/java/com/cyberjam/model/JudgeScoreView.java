package com.cyberjam.model;

public class JudgeScoreView {
    private String judgeId;
    private TeamRoleScore roleScore;
    private TeamThemeScore themeScore;

    // Default constructor
    public JudgeScoreView() {}

    // Parameterized constructor
    public JudgeScoreView(String judgeId, TeamRoleScore roleScore, TeamThemeScore themeScore) {
        this.judgeId = judgeId;
        this.roleScore = roleScore;
        this.themeScore = themeScore;
    }

    // Getters and Setters
    public String getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId;
    }

    public TeamRoleScore getRoleScore() {
        return roleScore;
    }

    public void setRoleScore(TeamRoleScore roleScore) {
        this.roleScore = roleScore;
    }

    public TeamThemeScore getThemeScore() {
        return themeScore;
    }

    public void setThemeScore(TeamThemeScore themeScore) {
        this.themeScore = themeScore;
    }
}