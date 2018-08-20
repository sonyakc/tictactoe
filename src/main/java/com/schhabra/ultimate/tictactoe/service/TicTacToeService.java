package com.schhabra.ultimate.tictactoe.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.schhabra.ultimate.tictactoe.data.Game;
import com.schhabra.ultimate.tictactoe.data.Move;
import com.schhabra.ultimate.tictactoe.data.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TicTacToeService {
    private static final String EMPTY_STRING = "";
    private static final int BOARD_SIZE = 9;
    private static long COUNTER = 0;
    private static final int[] validSubGames = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final Map<Long, Game> gameDatabase = new HashMap<>();
    private static Player currentPlayer;

    public void resetCounter() {
        COUNTER = 0;
    }

    public Game initGame() {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = EMPTY_STRING;
            }
        }
        final Game game = Game.builder().id(++COUNTER).board(board).turn(Player.X).validSubgames(validSubGames).build();
        currentPlayer = Player.X;
        gameDatabase.put(game.getId(), game);
        return game;
    }

    public Game move(final Move move) {
        final Game game = gameDatabase.get(move.getId());

        boolean isValidMove = false;
        final int[] validSubgames = game.getValidSubgames();
        for (int i = 0; i < validSubgames.length; i++) {
            if (validSubgames[i] == move.getSubgame()) {
                isValidMove = true;
                break;
            }
        }

        if (!isValidMove) {
            throw new MoveException("Cell not valid");
        }

        final String[][] board = game.getBoard();
        if (EMPTY_STRING.equals(board[move.getSubgame()][move.getCell()])) {
            board[move.getSubgame()][move.getCell()] = currentPlayer.toString();
        } else {
            throw new MoveException("Cell occupied");
        }

        assignPlayer();
        game.setTurn(currentPlayer);
        game.setValidSubgames(new int[]{move.getCell()});
        return game;
    }

    private void assignPlayer() {
        if (Player.X.equals(currentPlayer)) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
    }

    public Game gameStatus(final Long id) {
        final Game game = gameDatabase.get(id);
        if (game == null) {
            throw new GameNotFoundException("Invalid id");
        }
        return game;
    }

//    List<Integer> diagonalWinCells = ImmutableList.of(0, 2, 4, 6, 8);

    boolean isWinner(final Game game, final Player currentPlayer, int currentSubgame) {
        final String[] currentGame = game.getBoard()[currentSubgame];
        String player = currentPlayer.toString();
        if ((player.equals(currentGame[0]) && player.equals(currentGame[1]) && player.equals(currentGame[2])) ||
                (player.equals(currentGame[3]) && player.equals(currentGame[4]) && player.equals(currentGame[5])) ||
                (player.equals(currentGame[6]) && player.equals(currentGame[7]) && player.equals(currentGame[8])) ||
                (player.equals(currentGame[0]) && player.equals(currentGame[3]) && player.equals(currentGame[6])) ||
                (player.equals(currentGame[1]) && player.equals(currentGame[4]) && player.equals(currentGame[7])) ||
                (player.equals(currentGame[2]) && player.equals(currentGame[5]) && player.equals(currentGame[8])) ||
                (player.equals(currentGame[0]) && player.equals(currentGame[4]) && player.equals(currentGame[8])) ||
                (player.equals(currentGame[2]) && player.equals(currentGame[4]) && player.equals(currentGame[6])) ) {
            return true;
        } else {
            return false;
        }
    }
}
