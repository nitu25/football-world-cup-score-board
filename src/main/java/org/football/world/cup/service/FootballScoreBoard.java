package org.football.world.cup.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *  This class contains methods for startGame,finish game, update score and game summary.
 */
public class FootballScoreBoard implements ScoreBoard {

    private final List<Game> games = new ArrayList<>();

    @Override
    public Game startGame(String homeTeam, String awayTeam, LocalDateTime start) {
        Game startedGame = new Game(homeTeam, awayTeam, start);
        addGame(startedGame);
        return startedGame;
    }

    private void addGame(final Game startedGame) {
        games.add(startedGame);
    }

    @Override
    public List<Game> getActiveGames() {
        return games;
    }

    @Override
    public void finishGame(final Game activeGame) {
        games.remove(activeGame);
    }

    @Override
    public Game getGame(final Game activeGame) {
        return games.stream()
                .filter(game -> game.equals(activeGame))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void updateGameScore(Game searchGame, Score score) {
        games.stream()
                .filter(game -> game.equals(searchGame))
                .findFirst()
                .ifPresent(game -> game.updateScore(score));
    }

    @Override
    public List<Game> getGamesSummary() {
        return games.stream()
                .sorted(
                        Comparator.comparing(Game::getGameTotalScores)
                                .thenComparing(Game::getStartDateTime).reversed()
                ).toList();
    }

    @Override
    public Game getGameByTeams(String homeTeam, String awayTeam) {
        return games.stream()
                .filter(game -> game.getHomeTeamName().equals(homeTeam) && game.getAwayTeamName().equals(awayTeam))
                .findFirst()
                .orElseThrow();
    }
}
