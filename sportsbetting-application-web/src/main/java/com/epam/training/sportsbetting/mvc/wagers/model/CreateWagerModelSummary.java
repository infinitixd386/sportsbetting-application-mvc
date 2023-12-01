package com.epam.training.sportsbetting.mvc.wagers.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateWagerModelSummary {

    private Long outcomeId;
    private String outcomeDescription;
    private BigDecimal outcomeOdd;
    private String outcomeWin;
    @NotNull(message = "Please enter the amount.")
    @Min(value = 0L, message = "The value must be positive.")
    private BigDecimal wagerAmount;
    private String wagerCurrency;

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
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

    public String getOutcomeWin() {
        return outcomeWin;
    }

    public void setOutcomeWin(String outcomeWin) {
        this.outcomeWin = outcomeWin;
    }

    public String getWagerCurrency() {
        return wagerCurrency;
    }

    public void setWagerCurrency(String wagerCurrency) {
        this.wagerCurrency = wagerCurrency;
    }

    public BigDecimal getWagerAmount() {
        return wagerAmount;
    }

    public void setWagerAmount(BigDecimal wagerAmount) {
        this.wagerAmount = wagerAmount;
    }
}
