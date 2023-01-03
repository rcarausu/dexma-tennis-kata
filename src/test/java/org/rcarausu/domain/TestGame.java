package org.rcarausu.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.rcarausu.domain.Game.*;

public class TestGame {

    @Test
    public void shouldAnnounceScoreCorrectly_whenAnyPlayerScoresAPoint() {
        // given
        var game = Game.newGame();

        // when
        game.serverScores();

        assertEquals("15:0", game.getScore());

        game.getServer().setPoints(1);
        game.getReceiver().setPoints(1);
        game.receiverScores();

        assertEquals("15:30", game.getScore());

        game.getServer().setPoints(2);
        game.getReceiver().setPoints(2);
        game.receiverScores();

        assertEquals("30:40", game.getScore());
    }

    @Test
    public void shouldAnnounceDeuceCorrectly() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(3);
        game.getReceiver().setPoints(2);
        game.receiverScores();
        assertEquals(DEUCE, game.getScore());

        game.getServer().setPoints(8);
        game.getReceiver().setPoints(9);
        game.serverScores();
        assertEquals(DEUCE, game.getScore());
    }

    @Test
    public void shouldAnnounceAdvantageCorrectly() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(3);
        game.getReceiver().setPoints(3);
        game.serverScores();
        assertEquals(SERVER_ADVANTAGE, game.getScore());

        game.getServer().setPoints(10);
        game.getReceiver().setPoints(10);
        game.receiverScores();
        assertEquals(RECEIVER_ADVANTAGE, game.getScore());
    }

    @Test
    public void shouldAnnounceWinningsCorrectly() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(16);
        game.getReceiver().setPoints(15);
        game.serverScores();
        assertEquals(SERVER_WON, game.getScore());

        game.getServer().setPoints(2);
        game.getReceiver().setPoints(3);
        game.receiverScores();
        assertEquals(RECEIVER_WON, game.getScore());
    }


}