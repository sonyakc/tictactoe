package com.schhabra.ultimate.tictactoe.service;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(final String message) {
        super(message);
    }
}
