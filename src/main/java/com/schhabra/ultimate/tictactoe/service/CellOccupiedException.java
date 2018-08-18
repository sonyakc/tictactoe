package com.schhabra.ultimate.tictactoe.service;

public class CellOccupiedException extends RuntimeException {
    public CellOccupiedException(final String message) {
        super(message);
    }
}
