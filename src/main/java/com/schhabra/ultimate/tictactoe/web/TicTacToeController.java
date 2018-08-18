package com.schhabra.ultimate.tictactoe.web;

import com.schhabra.ultimate.tictactoe.data.Game;
import com.schhabra.ultimate.tictactoe.data.Move;
import com.schhabra.ultimate.tictactoe.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicTacToeController {

    private final TicTacToeService service;

    @Autowired
    public TicTacToeController(final TicTacToeService service) {
        this.service = service;
    }

    @PostMapping("game")
    public ResponseEntity<Game> initGame() {
        final Game game = service.initGame();
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @GetMapping("game")
    public ResponseEntity<Game> gameStatus(@RequestParam final Long id) {
        final Game game = service.gameStatus(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping("move")
    public ResponseEntity<Game> move(@RequestParam final Long id, @RequestParam final Integer subgame,
                                     @RequestParam final Integer cell) {
        final Game game = service.move(Move.builder().cell(cell).subgame(subgame).id(id).build());
        return ResponseEntity.ok(game);
    }
}
