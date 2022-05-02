package org.football.world.cup.service;

import java.time.LocalDateTime;

public class Game {

  private final Team homeTeam;
  private final Team awayTeam;
  private Score score;
  private final LocalDateTime startDateTime;

    public Game(String homeTeam, String awayTeam, LocalDateTime startDateTime) {
        this.homeTeam = new FootballTeam(homeTeam);
        this.awayTeam = new FootballTeam(awayTeam);
        this.score = new Score();
        this.startDateTime = startDateTime;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public int getAwayTeamScore() {
        return score.getAwayTeamScore();
    }

    public int getHomeTeamScore() {
        return score.getHomeTeamScore();
    }

    public void updateScore(Score score) {
        this.score = score;
    }

    public int getGameTotalScores() {
        return score.getTotalScore();
    }

    public String getAwayTeamName() {
        return awayTeam.getTeamName();
    }

    public String getHomeTeamName() {
        return homeTeam.getTeamName();
    }
}
