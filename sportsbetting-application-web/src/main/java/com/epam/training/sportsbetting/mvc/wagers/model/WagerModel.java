package com.epam.training.sportsbetting.mvc.wagers.model;

import javax.validation.constraints.NotNull;

public class WagerModel {

    private Long outcomeId;
    @NotNull
    private String wagerAmount;

    public WagerModel() {
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public String getWagerAmount() {
        return wagerAmount;
    }

    public void setWagerAmount(String wagerAmount) {
        this.wagerAmount = wagerAmount;
    }
}
