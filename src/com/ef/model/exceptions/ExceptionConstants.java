package com.ef.model.exceptions;

public enum ExceptionConstants {

    ARGUMENT_PARSER_EXCEPTION_DURATION(
        "Exception parsing the Duration entered. Please enter a valid duration, it must be 'daily' or 'hourly'."),
    ARGUMENT_PARSER_EXCEPTION_DATE(
        "Exception parsing the Start Date entered. Please enter a valid start date using the pattern: yyyy-MM-dd.HH:mm:ss"),
    ARGUMENT_PARSER_EXCEPTION_THRESHOLD(
        "Exception parsing the Threshold entered. Please enter a valid threshold, it must be an integer."),
    LOG_PARSER_EXCEPTION_INVALID_LINE("Log file line has more or less than 5 values to parse."),
    LOG_PARSER_EXCEPTION_INVALID_DATE("Exception when trying to parse the date of one of the records."),
    LOG_EVENT_ID_EXCEPTION_AUTO_GEN("Exception occurred generating the ID from mysql. Please try again."),
    SQL_INSERT_EXCEPTION_LOG_EVENT("Exception when inserting to the logevent table. Please try again"),
    SQL_INSERT_EXCEPTION_REQUEST_LOG("Exception when inserting to the requestlog table. Please try again"),
    SQL_INSERT_EXCEPTION_BLOCKED_REQUEST("Exception when inserting to the blockedrequest table. Please try again");

    private final String exception;

    /**
     * @param exception the name of the exception
     */
    ExceptionConstants(String exception) {
        this.exception = exception;
    }

    /**
     * @return Returns the full text of the exception
     */
    @Override public String toString() {
        return this.exception;
    }
}
