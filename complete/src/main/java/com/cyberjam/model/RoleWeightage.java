package com.cyberjam.model;

public class RoleWeightage {
    private double softwareWeight;
    private double hardwareWeight;
    private double visualArtWeight;
    private double musicWeight;
    private double wildCardWeight;

    // Default constructor
    public RoleWeightage() {}

    // Parameterized constructor
    public RoleWeightage(double softwareWeight, double hardwareWeight, double visualArtWeight, double musicWeight, double wildCardWeight) {
        this.softwareWeight = softwareWeight;
        this.hardwareWeight = hardwareWeight;
        this.visualArtWeight = visualArtWeight;
        this.musicWeight = musicWeight;
        this.wildCardWeight = wildCardWeight;
    }

    // Getters and Setters
    public double getSoftwareWeight() {
        return softwareWeight;
    }

    public void setSoftwareWeight(double softwareWeight) {
        this.softwareWeight = softwareWeight;
    }

    public double getHardwareWeight() {
        return hardwareWeight;
    }

    public void setHardwareWeight(double hardwareWeight) {
        this.hardwareWeight = hardwareWeight;
    }

    public double getVisualArtWeight() {
        return visualArtWeight;
    }

    public void setVisualArtWeight(double visualArtWeight) {
        this.visualArtWeight = visualArtWeight;
    }

    public double getMusicWeight() {
        return musicWeight;
    }

    public void setMusicWeight(double musicWeight) {
        this.musicWeight = musicWeight;
    }

    public double getWildCardWeight() {
        return wildCardWeight;
    }

    public void setWildCardWeight(double wildCardWeight) {
        this.wildCardWeight = wildCardWeight;
    }
}