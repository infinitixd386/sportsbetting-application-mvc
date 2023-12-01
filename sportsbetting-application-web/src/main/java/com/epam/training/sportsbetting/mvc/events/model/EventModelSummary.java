package com.epam.training.sportsbetting.mvc.events.model;

import java.time.LocalDateTime;

public class EventModelSummary {

    private Long eventId;
    private String eventTitle;
    private String eventStartdate;
    private String eventType;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventStartdate() {
        return eventStartdate;
    }

    public void setEventStartdate(String eventStartdate) {
        this.eventStartdate = eventStartdate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
