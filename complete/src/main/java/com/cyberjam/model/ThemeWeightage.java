package com.cyberjam.model;

public class ThemeWeightage {
    private double aiWeight;
    private double fashionWeight;
    private double governanceWeight;
    private double sportsGamingWeight;
    private double securityPrivacyWeight;

    // Default constructor
    public ThemeWeightage() {}

    // Parameterized constructor
    public ThemeWeightage(double aiWeight, double fashionWeight, double governanceWeight, double sportsGamingWeight, double securityPrivacyWeight) {
        this.aiWeight = aiWeight;
        this.fashionWeight = fashionWeight;
        this.governanceWeight = governanceWeight;
        this.sportsGamingWeight = sportsGamingWeight;
        this.securityPrivacyWeight = securityPrivacyWeight;
    }

    // Getters and Setters
    public double getAiWeight() {
        return aiWeight;
    }

    public void setAiWeight(double aiWeight) {
        this.aiWeight = aiWeight;
    }

    public double getFashionWeight() {
        return fashionWeight;
    }

    public void setFashionWeight(double fashionWeight) {
        this.fashionWeight = fashionWeight;
    }

    public double getGovernanceWeight() {
        return governanceWeight;
    }

    public void setGovernanceWeight(double governanceWeight) {
        this.governanceWeight = governanceWeight;
    }

    public double getSportsGamingWeight() {
        return sportsGamingWeight;
    }

    public void setSportsGamingWeight(double sportsGamingWeight) {
        this.sportsGamingWeight = sportsGamingWeight;
    }

    public double getSecurityPrivacyWeight() {
        return securityPrivacyWeight;
    }

    public void setSecurityPrivacyWeight(double securityPrivacyWeight) {
        this.securityPrivacyWeight = securityPrivacyWeight;
    }
}