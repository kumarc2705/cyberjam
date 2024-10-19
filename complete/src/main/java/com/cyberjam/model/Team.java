package com.cyberjam.model;

import java.util.List;

public class Team {
    private String id;
    private String name;
    private List<Person> members;
    

    // Default constructor
    public Team() {}

    // Parameterized constructor
    public Team(String id, String name, List<Person> members) {
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

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    // Method to add a team member
    public void addMember(Person member) {
      this.members.add(member);
  }

  // Method to remove a team member
  public boolean removeMember(String memberName) {
      return this.members.removeIf(member -> member.getName().equals(memberName));
  }
}