package com.schhabra.ultimate.tictactoe.service;

import com.schhabra.ultimate.tictactoe.data.Game;
import com.schhabra.ultimate.tictactoe.data.Move;
import com.schhabra.ultimate.tictactoe.data.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TicTacToeService.class})
public class TicTacToeServiceTest extends BaseServiceTest {

    @Autowired
    private TicTacToeService service;
    private Game game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        service.resetCounter();
        game = service.initGame();
    }

    @Test
    public void shouldInitNameGame() {
        assertThat(game.getId()).isEqualTo(1);
        assertThat(game.getBoard()[0][0]).isEmpty();
        assertThat(game.getTurn()).isEqualTo(Player.X);
        assertThat(game.getValidSubgames()).isEqualTo(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        assertThat(game.getWinner()).isEmpty();
    }

    @Test
    public void shouldMoveSuccessfully() {
        final Game game = service.move(Move.builder().id(1L).cell(8).subgame(3).build());
        assertThat(game.getTurn()).isEqualTo(Player.O);
        assertThat(game.getBoard()[3][8]).isEqualTo(Player.X.toString());
        assertThat(game.getValidSubgames()).isEqualTo(new int[]{8});
        assertThat(game.getWinner()).isEmpty();

        service.move(Move.builder().id(1L).cell(2).subgame(8).build());
        assertThat(game.getTurn()).isEqualTo(Player.X);
        assertThat(game.getBoard()[8][2]).isEqualTo(Player.O.toString());
        assertThat(game.getValidSubgames()).isEqualTo(new int[]{2});
        assertThat(game.getWinner()).isEmpty();
    }

    @Test
    public void shouldNotMoveSuccessfully() {
        service.move(Move.builder().id(1L).cell(8).subgame(3).build());

        exception.expect(MoveException.class);
        exception.expectMessage("Cell not valid");

        service.move(Move.builder().id(1L).cell(4).subgame(3).build());
    }

    @Test
    public void shouldDetectPlayerWonGame() {
        //construct a winning board for a given subgame
        String[][] board = new String[9][9];
        board[0][3] = Player.X.toString();
        board[0][4] = Player.X.toString();
        board[0][5] = Player.X.toString();
        final int subgame = 0;
        final Player currentPlayer = Player.X;
        Game winningGame = Game.builder().board(board).build();

        final boolean isWinner = service.isWinner(winningGame, currentPlayer, subgame);

        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldDetectPlayerDidNotWinGame() {
        //construct a winning board for a given subgame
        String[][] board = new String[9][9];
        board[1][3] = Player.X.toString();
        board[1][4] = Player.O.toString();
        board[1][5] = Player.X.toString();
        board[2][1] = Player.X.toString();
        board[2][5] = Player.X.toString();
        board[2][8] = Player.O.toString();
        final int subgame = 1;
        final Player currentPlayer = Player.O;
        Game winningGame = Game.builder().board(board).build();

        final boolean isWinner = service.isWinner(winningGame, currentPlayer, subgame);

        assertThat(isWinner).isFalse();
    }
}


