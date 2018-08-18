package com.schhabra.ultimate.tictactoe.service;

import com.schhabra.ultimate.tictactoe.data.Game;
import com.schhabra.ultimate.tictactoe.data.Move;
import com.schhabra.ultimate.tictactoe.data.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicTacToeService {
    private static final int BOARD_SIZE = 9;
    private static long COUNTER = 0;
    private static final int[] validSubGames = new int[] {0,1,2,3,4,5,6,7,8};
    private static final Map<Long, Game> gameDatabase = new HashMap<>();
    private static Player currentPlayer;

    public void resetCounter() {
        COUNTER = 0;
    }

    public Game initGame() {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = "";
            }
        }
        final Game game = Game.builder().id(++COUNTER).board(board).turn(Player.X).validSubgames(validSubGames).build();
        currentPlayer = Player.X;
        gameDatabase.put(game.getId(), game);
        return game;
    }

    public Game move(final Move move) {
        final Game game = gameDatabase.get(move.getId());
        final String[][] board = game.getBoard();

        if("".equals(board[move.getSubgame()][move.getCell()])) {
            board[move.getSubgame()][move.getCell()] = currentPlayer.toString();
        } else {
            throw new CellOccupiedException("Cell occupied");
        }

        if(Player.X.equals(currentPlayer)) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
        game.setTurn(currentPlayer);
        game.setValidSubgames(new int[] {move.getCell()});
        return game;
    }

    public Game gameStatus(final Long id) {
        final Game game = gameDatabase.get(id);
        if(game == null) {
            throw new GameNotFoundException("Invalid id");
        }
        return game;
    }
}
