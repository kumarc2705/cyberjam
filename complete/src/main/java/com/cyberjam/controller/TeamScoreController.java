package com.cyberjam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamScoreController {

    @GetMapping("/team-scores")
    public String getTeamScorePage() {
        return "team-scores"; // This should match the name of your HTML file without the .html extension
    }
}