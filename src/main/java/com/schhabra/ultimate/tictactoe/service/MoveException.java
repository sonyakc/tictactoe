package com.schhabra.ultimate.tictactoe.service;

public class MoveException extends RuntimeException {
    public MoveException(final String message) {
        super(message);
    }
}
