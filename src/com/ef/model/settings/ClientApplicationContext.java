package com.ef.model.settings;

import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Class used to store the argument values passed in from the User on java console app startup.
 */
public class ClientApplicationContext {

    private String accessLogPath;
    private Date startDate;
    private String duration;
    private Integer threshold;

    public ClientApplicationContext() {
    }

    public String getAccessLogPath() {
        return accessLogPath;
    }

    public void setAccessLogPath(String accessLogPath) {
        this.accessLogPath = accessLogPath;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    /**
     * Method used to get the end date using the start date and the duration from the arguments.
     * @return - The end Date using the start date and duration.
     */
    public Date getEndDate() {
        return Date.from(this.startDate.toInstant().plus(1,
            (this.duration.equalsIgnoreCase("hourly") ? ChronoUnit.HOURS : ChronoUnit.DAYS)));
    }

    @Override public String toString() {
        return "ClientApplicationContext{" + "accessLogPath='" + accessLogPath + '\'' +
            ", startDate=" + startDate + ", duration='" + duration + '\'' + ", threshold=" +
            threshold + '}';
    }
}
