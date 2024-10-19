package com.cyberjam.model;

public class TeamRoleScore {
    private double softwareScore;
    private double hardwareScore;
    private double visualArtScore;
    private double musicScore;
    private double wildCardScore;

    // Default constructor
    public TeamRoleScore() {}

    // Parameterized constructor
    public TeamRoleScore(double softwareScore, double hardwareScore, double visualArtScore, double musicScore, double wildCardScore) {
        this.softwareScore = softwareScore;
        this.hardwareScore = hardwareScore;
        this.visualArtScore = visualArtScore;
        this.musicScore = musicScore;
        this.wildCardScore = wildCardScore;
    }

    // Getters and Setters
    public double getSoftwareScore() {
        return softwareScore;
    }

    public void setSoftwareScore(double softwareScore) {
        this.softwareScore = softwareScore;
    }

    public double getHardwareScore() {
        return hardwareScore;
    }

    public void setHardwareScore(double hardwareScore) {
        this.hardwareScore = hardwareScore;
    }

    public double getVisualArtScore() {
        return visualArtScore;
    }

    public void setVisualArtScore(double visualArtScore) {
        this.visualArtScore = visualArtScore;
    }

    public double getMusicScore() {
        return musicScore;
    }

    public void setMusicScore(double musicScore) {
        this.musicScore = musicScore;
    }

    public double getWildCardScore() {
        return wildCardScore;
    }

    public void setWildCardScore(double wildCardScore) {
        this.wildCardScore = wildCardScore;
    }
}