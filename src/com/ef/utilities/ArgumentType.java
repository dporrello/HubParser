package com.ef.utilities;

public enum ArgumentType {

    ACCESSLOG("accesslog"),
    STARTDATE("startdate"),
    DURATION("duration"),
    THRESHOLD("threshold");

    private final String argumentName;

    ArgumentType(String argumentName) { this.argumentName = argumentName; }

    public String toString() { return this.argumentName; }
}
