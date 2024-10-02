package com.example.boardgamecreation;

public class ChessBoardGame extends BoardGame{
    private String boardGameName;
    public ChessBoardGame(String boardGameName) {
        this.boardGameName = boardGameName;
    }

    public String getBoardGameName() {
        return boardGameName;
    }

    public void playGame() {
        //Implement logic for playing Chess
        System.out.println("Playing Chess");
    }

    public GameType gameType() {
        return GameType.CHESS;
    }
}
