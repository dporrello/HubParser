package com.ef.model.exceptions;

import java.io.IOException;

public class LogFileIOException
    extends IOException {

    public LogFileIOException(String logPath) {
        super(String.format("Exception trying to read the existing log file from %s", logPath));
    }
}
