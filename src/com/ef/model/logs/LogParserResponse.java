package com.ef.model.logs;

import java.util.*;

public class LogParserResponse {
    private List<LogRecord> logRecordList;
    private Map<String, List<LogRecord>> logRecordMap;

    public LogParserResponse() {
        this.logRecordList = new ArrayList<>();
        this.logRecordMap = new HashMap<>();
    }

    public List<LogRecord> getLogRecordList() {
        return logRecordList;
    }

    public void setLogRecordList(List<LogRecord> logRecordList) {
        this.logRecordList = logRecordList;
    }

    public void setLogRecordMap(Map<String, List<LogRecord>> logRecordMap) {
        this.logRecordMap = logRecordMap;
    }

    public List<String> getIpAddressesPastThreshold(Date startDate, Date endDate, Integer threshold) {
        List<String> ipAddressList = new ArrayList<>();

        for (Map.Entry<String, List<LogRecord>> mapEntry : this.logRecordMap.entrySet()) {
            // First we should check to see if there are even enough requests made for this specific
            // vin to reach the threshold without looking at the startdate and duration. More efficient
            // this way.
            if (mapEntry.getValue().size() < threshold)
                continue;

            // At this point we know that there have been more requests made than the threshold, now
            // we need to check the date and the duration.
            if (isIpAddressOverThreshold(mapEntry.getValue(), startDate, endDate, threshold))
                ipAddressList.add(mapEntry.getKey());
        }

        return ipAddressList;
    }

    private Boolean isIpAddressOverThreshold(List<LogRecord> logRecordList, Date startDate,
        Date endDate, Integer threshold) {

        // Iterate through the list to check the dates. Use a count instead of another array list to
        // keep the memory usage down
        int count = 0;
        for (LogRecord logRecord : logRecordList) {
            if (logRecord.getDate().after(startDate) && logRecord.getDate().before(endDate))
                count++;
            // If we reach the threshold there is no need to continue iterating through the list.
            // More efficient this way.
            if (count >= threshold)
                break;
        }

        return count >= threshold;
    }
}
