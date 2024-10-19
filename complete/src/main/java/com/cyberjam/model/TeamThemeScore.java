package com.cyberjam.model;

public class TeamThemeScore {
    private double aiScore;
    private double fashionScore;
    private double governanceScore;
    private double sportsGamingScore;
    private double securityPrivacyScore;

    // Default constructor
    public TeamThemeScore() {}

    // Parameterized constructor
    public TeamThemeScore(double aiScore, double fashionScore, double governanceScore, double sportsGamingScore, double securityPrivacyScore) {
        this.aiScore = aiScore;
        this.fashionScore = fashionScore;
        this.governanceScore = governanceScore;
        this.sportsGamingScore = sportsGamingScore;
        this.securityPrivacyScore = securityPrivacyScore;
    }

    // Getters and Setters
    public double getAiScore() {
        return aiScore;
    }

    public void setAiScore(double aiScore) {
        this.aiScore = aiScore;
    }

    public double getFashionScore() {
        return fashionScore;
    }

    public void setFashionScore(double fashionScore) {
        this.fashionScore = fashionScore;
    }

    public double getGovernanceScore() {
        return governanceScore;
    }

    public void setGovernanceScore(double governanceScore) {
        this.governanceScore = governanceScore;
    }

    public double getSportsGamingScore() {
        return sportsGamingScore;
    }

    public void setSportsGamingScore(double sportsGamingScore) {
        this.sportsGamingScore = sportsGamingScore;
    }

    public double getSecurityPrivacyScore() {
        return securityPrivacyScore;
    }

    public void setSecurityPrivacyScore(double securityPrivacyScore) {
        this.securityPrivacyScore = securityPrivacyScore;
    }
}