package org.football.world.cup.service;

public class Score {

    private final int homeTeamScore;
    private final int awayTeamScore;

    /**
     * initially score will be 0 for both the team.
     */
    public Score(){
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;

    }

    public Score(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public int getTotalScore(){
        return homeTeamScore + awayTeamScore;
    }
}
