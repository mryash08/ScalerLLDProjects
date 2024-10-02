package com.example.boardgamecreation;

public abstract class BoardGame {
    public String  gameName;
    public abstract void playGame();
    public abstract String getBoardGameName();
    public abstract GameType gameType();
}
