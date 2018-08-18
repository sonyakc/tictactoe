package com.schhabra.ultimate.tictactoe.service;

import com.schhabra.ultimate.tictactoe.data.Game;
import com.schhabra.ultimate.tictactoe.data.Move;
import com.schhabra.ultimate.tictactoe.data.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicTacToeService.class)
public class TicTacToeServiceTest {

    @Autowired
    private TicTacToeService service;
    private Game game;

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
    }

    @Test
    public void shouldNotMoveSuccessfully() {
        final Game game = service.move(Move.builder().id(1L).cell(8).subgame(3).build());
        assertThat(game.getTurn()).isEqualTo(Player.O);
        assertThat(game.getBoard()[3][8]).isEqualTo(Player.X.toString());
        assertThat(game.getValidSubgames()).isEqualTo(new int[]{8});
        assertThat(game.getWinner()).isEmpty();
    }
}


