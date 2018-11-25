package com.ef.dao.logs;

public enum LogQuery {

    INSERT_LOG_REQUESTS(
        "INSERT INTO requestlog (id, ipAddress, requestDate, request, requestStatus, userAgent) VALUES %s"),
    INSERT_BLOCKED_REQUEST(
        "INSERT INTO blockedrequest (id, ipAddress, comment) VALUES %s"),
    INSERT_LOG_EVENT("INSERT INTO eventlog (fileName, date) VALUES (?,?)");

    private final String queryName;

    /**
     * @param queryName the name of the query
     */
    LogQuery(String queryName) {
        this.queryName = queryName;
    }

    /**
     * @return Returns the full text of the query
     */
    @Override public String toString() {
        return this.queryName;
    }

}
