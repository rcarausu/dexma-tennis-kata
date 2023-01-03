package org.rcarausu.domain;

public class Player {

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

    public void setPoints(int points) {
        assert points >= 0;

        this.points = points;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public void score() {
        this.points += 1;
    }

}
