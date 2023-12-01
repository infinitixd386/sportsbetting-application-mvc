package com.epam.training.sportsbetting.mvc.wagers.model;

import java.util.List;

public class CreateWagerFormModel {

    private List<CreateWagerModelSummary> createWagerSummary;
    private Long eventId;
    private String eventTitle;
    private String eventStartdate;
    private String eventType;
    private Long betId;
    private String betDescription;
    private String playerCurrency;

    public CreateWagerFormModel(List<CreateWagerModelSummary> createWagerSummary, Long eventId, String eventTitle, String eventStartdate, String eventType, Long betId, String betDescription, String playerCurrency) {
        this.createWagerSummary = createWagerSummary;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventStartdate = eventStartdate;
        this.eventType = eventType;
        this.betId = betId;
        this.betDescription = betDescription;
        this.playerCurrency = playerCurrency;
    }

    public List<CreateWagerModelSummary> getCreateWagerSummary() {
        return createWagerSummary;
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

    public Long getBetId() {
        return betId;
    }

    public String getBetDescription() {
        return betDescription;
    }

    public String getPlayerCurrency() {
        return playerCurrency;
    }
}
