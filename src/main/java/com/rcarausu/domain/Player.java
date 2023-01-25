package com.rcarausu.domain;

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

    // helper method for tests, maybe there is a better way of doing this
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