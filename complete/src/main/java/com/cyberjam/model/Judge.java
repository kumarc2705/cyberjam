package com.cyberjam.model;

public class Judge extends Person {
    private String judgeId;
    private RoleWeightage roleWeightage;
    private ThemeWeightage themeWeightage;

    // Default constructor
    public Judge() {
        super();
    }

    // Parameterized constructor
    public Judge(String judgeId, String name, String experience, String role, String description, RoleWeightage roleWeightage, ThemeWeightage themeWeightage) {
        super(name, experience, role, description);
        this.judgeId = judgeId;
        this.roleWeightage = roleWeightage;
        this.themeWeightage = themeWeightage;
    }

    // Getters and Setters
    public String getJudgeId() {
        return this.judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId;
    }
    public RoleWeightage getRoleWeightage() {
        return roleWeightage;
    }

    public void setRoleWeightage(RoleWeightage roleWeightage) {
        this.roleWeightage = roleWeightage;
    }

    public ThemeWeightage getThemeWeightage() {
        return themeWeightage;
    }

    public void setThemeWeightage(ThemeWeightage themeWeightage) {
        this.themeWeightage = themeWeightage;
    }
}