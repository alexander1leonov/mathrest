package org.alexander1leonov.mathrest.domain;

public class WaitTimeCurrent {
    private String airportCode;
    private String airportName;
    private String queueId;
    private String queueName;
    private int projectedWaitTime;
    private int projectedMinWaitMinutes;
    private int projectedMaxWaitMinutes;
    private String localTime;
    private String time;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public int getProjectedWaitTime() {
        return projectedWaitTime;
    }

    public void setProjectedWaitTime(int projectedWaitTime) {
        this.projectedWaitTime = projectedWaitTime;
    }

    public int getProjectedMinWaitMinutes() {
        return projectedMinWaitMinutes;
    }

    public void setProjectedMinWaitMinutes(int projectedMinWaitMinutes) {
        this.projectedMinWaitMinutes = projectedMinWaitMinutes;
    }

    public int getProjectedMaxWaitMinutes() {
        return projectedMaxWaitMinutes;
    }

    public void setProjectedMaxWaitMinutes(int projectedMaxWaitMinutes) {
        this.projectedMaxWaitMinutes = projectedMaxWaitMinutes;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
