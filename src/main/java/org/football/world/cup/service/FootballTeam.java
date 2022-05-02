package org.football.world.cup.service;

/**
 *  This class will provide team name.
 */
public class FootballTeam implements Team{

    private final String teamName;

    public FootballTeam(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
