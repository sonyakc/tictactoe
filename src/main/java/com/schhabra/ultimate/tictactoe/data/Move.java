package com.schhabra.ultimate.tictactoe.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Move {
    private Long id;
    private int subgame;
    private int cell;
}
