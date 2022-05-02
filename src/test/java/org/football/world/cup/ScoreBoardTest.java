package org.football.world.cup;

import org.football.world.cup.service.Game;
import org.football.world.cup.service.FootballScoreBoard;
import org.football.world.cup.service.Team;
import org.football.world.cup.service.Score;
import org.football.world.cup.service.ScoreBoard;
;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScoreBoardTest {

    private ScoreBoard board;
    private static final LocalDateTime gameStartDate = LocalDateTime
            .of(2022,04, 29, 12, 0, 0);

    @BeforeEach
    public void init() {
        board = new FootballScoreBoard();
    }

    @Test
    public void should_start_game_with_initial_score() {

        board.startGame("Mexico", "Canada", gameStartDate);
        List<Game> activeGames = board.getActiveGames();
        Game activeGame = activeGames.get(0);

        assertEquals(1, activeGames.size());
        assertEquals(0, activeGame.getAwayTeamScore());
        assertEquals(0, activeGame.getHomeTeamScore());
    }

    @Test
    public void should_remove_match_when_game_finish() {

        Game startedGame = board.startGame("Mexico", "Canada", gameStartDate);
        board.finishGame(startedGame);
        assertFalse(board.getActiveGames().contains(startedGame));
    }

    @Test
    public void should_update_score_for_given_game() {

        Game startedGame = board.startGame("Mexico", "Canada", gameStartDate);
        board.updateGameScore(startedGame, new Score(0, 5));
        Game gameWithUpdatedScore = board.getGame(startedGame);

        Team home = gameWithUpdatedScore.getHomeTeam();
        Team away = gameWithUpdatedScore.getAwayTeam();

        assertEquals("Mexico", home.getTeamName());
        assertEquals(0, gameWithUpdatedScore.getHomeTeamScore());

        assertEquals("Canada", away.getTeamName());
        assertEquals(5, gameWithUpdatedScore.getAwayTeamScore());
    }

    @Test
    public void should_display_game_summary_order_by_and_most_recent() {

        prepareGameData();
        List<Game> games = board.getGamesSummary();
        Game expectedFirstGame = board.getGameByTeams("Uruguay", "Italy");
        Game expectedSecondGame = board.getGameByTeams("Spain", "Brazil");
        Game expectedLastGame = board.getGameByTeams("Germany", "France");

        assertEquals(games.get(0), expectedFirstGame);
        assertEquals(games.get(1), expectedSecondGame);
        assertEquals(games.get(games.size()-1), expectedLastGame);
    }

    private void prepareGameData() {
        Game uruguayItalyGame = board.startGame("Uruguay", "Italy", gameStartDate);
        Game spainBrazilGame = board.startGame("Spain", "Brazil", gameStartDate.minusHours(2));
        Game mexicoCanadaGame = board.startGame("Mexico", "Canada", gameStartDate.minusDays(1));
        Game argentinaAustralia = board.startGame("Argentina", "Australia", gameStartDate.minusDays(1));
        Game germanyFranceGame = board.startGame("Germany", "France", gameStartDate.minusDays(2));
        board.updateGameScore(mexicoCanadaGame, new Score(0, 5));
        board.updateGameScore(spainBrazilGame, new Score (10, 2));
        board.updateGameScore(germanyFranceGame, new Score (2,2));
        board.updateGameScore(uruguayItalyGame, new Score (6, 6));
        board.updateGameScore(argentinaAustralia, new Score(3,1));
    }
}
