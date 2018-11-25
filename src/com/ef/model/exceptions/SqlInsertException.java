package com.ef.model.exceptions;

import java.sql.SQLException;

public class SqlInsertException extends SQLException {

    public SqlInsertException(String message) {
        super(message);
    }
}
