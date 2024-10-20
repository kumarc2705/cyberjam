package com.cyberjam.model;

public class Participant extends Person {
    private String participantId;

    // Default constructor
    public Participant() {
        super();
    }

    // Parameterized constructor
    public Participant(String participantId, String name, String experience, String role, String description) {
        super(name, experience, role, description);
        this.participantId = participantId;
    }

    // Getters and Setters
    public String getParticipantId() {
        return this.participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }
}