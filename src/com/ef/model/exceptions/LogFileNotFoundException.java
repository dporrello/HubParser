package com.ef.model.exceptions;

public class LogFileNotFoundException extends Exception {

    public LogFileNotFoundException(String accessLogPath) {
        super(
            String.format("%s log path is not found. Please enter valid log path.", accessLogPath));
    }
}
