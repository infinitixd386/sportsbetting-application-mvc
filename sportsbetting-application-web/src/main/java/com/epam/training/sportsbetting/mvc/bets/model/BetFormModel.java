package com.epam.training.sportsbetting.mvc.bets.model;

import java.util.List;

public class BetFormModel {

    private List<BetModelSummary> bets;
    private Long eventId;
    private String eventTitle;
    private String eventStartdate;
    private String eventType;

    public BetFormModel(List<BetModelSummary> bets, Long eventId, String eventTitle, String eventStartdate, String eventType) {
        this.bets = bets;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventStartdate = eventStartdate;
        this.eventType = eventType;
    }

    public List<BetModelSummary> getBets() {
        return bets;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventStartdate() {
        return eventStartdate;
    }

    public String getEventType() {
        return eventType;
    }
}
