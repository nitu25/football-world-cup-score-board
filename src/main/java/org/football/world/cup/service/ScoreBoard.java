package org.football.world.cup.service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Score Board interface will have all the methods for football score board.
 */
public interface ScoreBoard {

    Game startGame(String homeTeam, String awayTeam, LocalDateTime start);

    void finishGame(Game activeGame);

    void updateGameScore(Game activeGame, Score score);

    List<Game> getGamesSummary();

    List<Game> getActiveGames();

    Game getGame(Game activeGame);

    Game getGameByTeams(String homeTeam, String awayTeam);

}
