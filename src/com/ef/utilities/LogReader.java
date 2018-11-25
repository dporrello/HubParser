package com.ef.utilities;

import com.ef.model.exceptions.ExceptionConstants;
import com.ef.model.exceptions.LogFileParserException;
import com.ef.model.logs.LogParserResponse;
import com.ef.model.logs.LogRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogReader {

    private final BufferedReader reader;
    private Map<String, List<LogRecord>> logRecordMap;

    public LogReader(Reader reader) {
        this.reader = new BufferedReader(reader);
        this.logRecordMap = new HashMap<>();
    }

    public LogParserResponse readLog()
        throws LogFileParserException, IOException {
        LogParserResponse response = new LogParserResponse();
        List<LogRecord> logRecordList = new ArrayList<>();
        String line;
        LogRecord logRecord;
        while (null != (line = this.reader.readLine())) {
            logRecord = parseLine(line);
            logRecordList.add(logRecord);
            if (logRecordMap.containsKey(logRecord.getIpAddress())) {
                logRecordMap.get(logRecord.getIpAddress()).add(logRecord);
            } else {
                logRecordMap.put(logRecord.getIpAddress(),
                    new ArrayList<>(Collections.singletonList(logRecord)));
            }
        }

        response.setLogRecordList(logRecordList);
        response.setLogRecordMap(logRecordMap);

        return response;
    }

    private static LogRecord parseLine(String line)
        throws LogFileParserException {
        LogRecord logRecord = new LogRecord();

        String[] splitString = line.split("\\|");

        if (splitString.length != 5)
            throw new LogFileParserException(
                ExceptionConstants.LOG_PARSER_EXCEPTION_INVALID_LINE.toString());

        int count = 0;
        for (String value : splitString) {
            switch (count++) {
                case 0:
                    try {
                        logRecord
                            .setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(value));
                    } catch (Exception exception) {
                        throw new LogFileParserException(
                            ExceptionConstants.LOG_PARSER_EXCEPTION_INVALID_DATE.toString());
                    }
                    break;
                case 1:
                    logRecord.setIpAddress(value);
                    break;
                case 2:
                    logRecord.setHttpRequest(value);
                    break;
                case 3:
                    logRecord.setHttpStatus(Integer.parseInt(value));
                    break;
                case 4:
                    logRecord.setUserAgent(value);
                    break;
                default:
                    throw new LogFileParserException(
                        ExceptionConstants.LOG_PARSER_EXCEPTION_INVALID_LINE.toString());
            }
        }

        return logRecord;
    }
}
