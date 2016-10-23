package com.vicky.notifier.view;

public class EventDetails {

    private String eventID = null;
    private final int day;
    private final String event;
    private final String month;

    public EventDetails(String eventID, int day, String month, String event) {
        this.eventID = eventID;
        this.day = day;
        this.month = month;
        this.event = event;
    }

    public EventDetails(int day, String month, String event) {
        this.day = day;
        this.month = month;
        this.event = event;
    }

    public String getEventID() {
        return eventID;
    }

    public int getDayAsInt() {
        return this.day;
    }

    public String getDay() {
        return Integer.toString(this.day);
    }

    public String getMonth() {
        return this.month;
    }

    public String getEvent() {
        return this.event;
    }
}
