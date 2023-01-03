package com.rcarausu.domain;

import static java.lang.String.format;
import static com.rcarausu.domain.Game.Player.PlayerType.RECEIVER;
import static com.rcarausu.domain.Game.Player.PlayerType.SERVER;

public class Game {

    private boolean finished = false;

    static class Player {

        public enum PlayerType {
            SERVER, RECEIVER
        }

        private int points = 0;

        private final PlayerType playerType;

        public Player(PlayerType playerType) {
            this.playerType = playerType;
        }

        public int getPoints() {
            return this.points;
        }

        // helper method for tests, maybe there is a better way of doing this
        public void setPoints(int points) {
            assert points >= 0;

            this.points = points;
        }

        public PlayerType getPlayerType() {
            return this.playerType;
        }

        private void score() {
            this.points += 1;
        }

    }

    public static final String DEUCE = "40:40";
    public static final String SERVER_ADVANTAGE = "A:40";
    public static final String RECEIVER_ADVANTAGE = "40:A";
    public static final String SERVER_WON = "Server won!";
    public static final String RECEIVER_WON = "Receiver won!";

    private final Player server;

    private final Player receiver;

    private Game(Player server, Player receiver) {
        this.server = server;
        this.receiver = receiver;
    }

    public static Game newGame() {
        return new Game(new Player(SERVER), new Player(RECEIVER));
    }

    public Player getServer() {
        return this.server;
    }

    public Player getReceiver() {
        return this.receiver;
    }

    public void serverScores() {
        if (serverWon() || receiverWon()) {
            finished = true;
        }

        assert !finished;
        this.server.score();
    }

    public void receiverScores() {
        if (serverWon() || receiverWon()) {
            finished = true;
        }

        assert !finished;

        this.receiver.score();
    }

    private boolean scoreIsDeuce() {
        return server.getPoints() >= 3 && server.getPoints() == receiver.getPoints();
    }

    private boolean scoreIsAdvantageForServer() {
        return receiver.getPoints() >= 3 && (receiver.getPoints() + 1) == server.getPoints();
    }

    private boolean scoreIsAdvantageForReceiver() {
        return server.getPoints() >= 3 && (server.getPoints() + 1) == receiver.getPoints();
    }

    private boolean serverWon() {
        return server.getPoints() > 3 && (server.getPoints() - receiver.getPoints()) >= 2;
    }

    private boolean receiverWon() {
        return receiver.getPoints() > 3 && (receiver.getPoints() - server.getPoints()) >= 2;
    }

    private String playerScore(int playerPoints) {
        switch (playerPoints) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            default:
                return "40";
        }
    }

    public String getScore() {
        if (scoreIsDeuce()) {
            return DEUCE;
        } else if (scoreIsAdvantageForServer()) {
            return SERVER_ADVANTAGE;
        } else if (scoreIsAdvantageForReceiver()) {
            return RECEIVER_ADVANTAGE;
        } else if (serverWon()) {
            return SERVER_WON;
        } else if (receiverWon()) {
            return RECEIVER_WON;
        } else {
            return format("%s:%s", playerScore(server.getPoints()), playerScore(receiver.getPoints()));
        }
    }

}
