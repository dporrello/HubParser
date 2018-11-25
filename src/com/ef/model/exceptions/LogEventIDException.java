package com.ef.model.exceptions;

import java.sql.SQLException;

public class LogEventIDException extends SQLException {

    public LogEventIDException(String message) {
        super(message);
    }
}
