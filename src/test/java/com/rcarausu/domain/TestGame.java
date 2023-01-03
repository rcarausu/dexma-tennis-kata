package com.rcarausu.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Assertions.assertEquals(Game.DEUCE, game.getScore());

        game.getServer().setPoints(8);
        game.getReceiver().setPoints(9);
        game.serverScores();
        Assertions.assertEquals(Game.DEUCE, game.getScore());
    }

    @Test
    public void shouldAnnounceAdvantageCorrectly() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(3);
        game.getReceiver().setPoints(3);
        game.serverScores();
        Assertions.assertEquals(Game.SERVER_ADVANTAGE, game.getScore());

        game.getServer().setPoints(10);
        game.getReceiver().setPoints(10);
        game.receiverScores();
        Assertions.assertEquals(Game.RECEIVER_ADVANTAGE, game.getScore());
    }

    @Test
    public void shouldAnnounceWinningsCorrectly() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(16);
        game.getReceiver().setPoints(15);
        game.serverScores();
        Assertions.assertEquals(Game.SERVER_WON, game.getScore());

        game.getServer().setPoints(2);
        game.getReceiver().setPoints(3);
        game.receiverScores();
        Assertions.assertEquals(Game.RECEIVER_WON, game.getScore());
    }

    @Test
    public void serverShouldNotBeAbleToScore_whenGameIsFinished() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(17);
        game.getReceiver().setPoints(15);

        assertThrows(AssertionError.class, game::serverScores);
    }

    @Test
    public void receiverShouldNotBeAbleToScore_whenGameIsFinished() {
        // given
        var game = Game.newGame();

        // when
        game.getServer().setPoints(17);
        game.getReceiver().setPoints(15);

        assertThrows(AssertionError.class, game::serverScores);
    }

}