package com.airwallex.calculator.exceptions;

public class ProcessingException extends RuntimeException {
    private String operator;
    private int position;
    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessingException(String op, int pos) {
        super(String.format("%s (position: %d)", op, pos));
        operator =op;
        position =pos;
    }

    public ProcessingException(String op) {
        super(String.format("Invalid operation %s", op));
    }

    public ProcessingException(String op, String message) {
        super(message);
        operator = op;
    }
    public ProcessingException(String op,int pos, String message) {
        super(String.format("operator %s(position: %d): %s", op, pos, message));
    }

    public String getOperator() {
        return operator;
    }

    public int getPosition() {
        return position;
    }
}

