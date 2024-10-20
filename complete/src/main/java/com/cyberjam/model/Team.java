package com.cyberjam.model;

import java.util.List;

public class Team {
    private String id;
    private String name;
    private List<Participant> members;
    

    // Default constructor
    public Team() {}

    // Parameterized constructor
    public Team(String id, String name, List<Participant> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participant> getMembers() {
        return members;
    }

    public void setMembers(List<Participant> members) {
        this.members = members;
    }

    // Method to add a team member
    public void addMember(Participant participant) {
        boolean exists = members.stream()
                .anyMatch(member -> member.getParticipantId().equals(participant.getParticipantId()));
        if (!exists) {
            this.members.add(participant);
        }
    }

  // Method to remove a team member
  public boolean removeMember(String memberName) {
      return this.members.removeIf(member -> member.getParticipantId().equals(memberName));
  }
}