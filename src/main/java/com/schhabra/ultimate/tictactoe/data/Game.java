package com.schhabra.ultimate.tictactoe.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private Long id;
    private String[][] board;
    private int[] validSubgames;
    private Player turn;

    @Builder.Default
    private String winner = "";
}
