package com.ef.model.logs;

import java.util.Date;

public class LogRecord {

    private Date date;
    private String ipAddress;
    private String httpRequest;
    private Integer httpStatus;
    private String userAgent;

    public LogRecord() { }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(String httpRequest) {
        this.httpRequest = httpRequest;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
