package com.epam.training.sportsbetting.mvc.homepage.model;

import java.math.BigDecimal;

public class WagerSummary {

    private Long wagerId;
    private String eventTitle;
    private String eventType;
    private String betDescription;
    private String outcomeDescription;
    private BigDecimal outcomeOdd;
    private String wagerAmount;
    private String wagerWin;
    private String wagerProcessed;

    public Long getWagerId() {
        return wagerId;
    }

    public void setWagerId(Long wagerId) {
        this.wagerId = wagerId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }


    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getBetDescription() {
        return betDescription;
    }

    public void setBetDescription(String betDescription) {
        this.betDescription = betDescription;
    }

    public String getOutcomeDescription() {
        return outcomeDescription;
    }

    public void setOutcomeDescription(String outcomeDescription) {
        this.outcomeDescription = outcomeDescription;
    }

    public BigDecimal getOutcomeOdd() {
        return outcomeOdd;
    }

    public void setOutcomeOdd(BigDecimal outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }

    public String getWagerAmount() {
        return wagerAmount;
    }

    public void setWagerAmount(String wagerAmount) {
        this.wagerAmount = wagerAmount;
    }

    public String getWagerWin() {
        return wagerWin;
    }

    public void setWagerWin(String wagerWin) {
        this.wagerWin = wagerWin;
    }

    public String getWagerProcessed() {
        return wagerProcessed;
    }

    public void setWagerProcessed(String wagerProcessed) {
        this.wagerProcessed = wagerProcessed;
    }
}
