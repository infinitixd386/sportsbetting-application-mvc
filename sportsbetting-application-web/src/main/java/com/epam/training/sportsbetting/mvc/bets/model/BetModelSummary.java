package com.epam.training.sportsbetting.mvc.bets.model;

import java.time.LocalDateTime;

public class BetModelSummary {

    private Long betId;
    private String betDescription;

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public String getBetDescription() {
        return betDescription;
    }

    public void setBetDescription(String betDescription) {
        this.betDescription = betDescription;
    }
}
