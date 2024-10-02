package com.example.boardgamecreation;

public class BoardGameFactory {
    // public BoardGameFactory(String gameName) {
    //     this.gameName = gameName;
    // }
    public static BoardGame createGame(GameType gameType,String gameName) {
        if(gameType.equals(GameType.CHESS)) {
            return new ChessBoardGame(gameName);
        } else if(gameType.equals(GameType.SNAKE_LADDER)) {
            return new SnakeLadderBoardGame(gameName);
        } else {
            return new TicTacToeBoardGame(gameName);
        }
    }
}
