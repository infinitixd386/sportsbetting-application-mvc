package com.epam.training.sportsbetting.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Wager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player player;
    @ManyToOne(cascade = CascadeType.ALL)
    private Outcome outcome;
    private BigDecimal amount;
    @Enumerated
    private Currency currency;
    private LocalDateTime timestampCreated;
    private boolean win;
    private boolean processed;

    @SuppressWarnings("checkstyle:ParameterNumber")
    public Wager(Player player, Outcome outcome, BigDecimal amount, Currency currency, LocalDateTime timestampCreated, boolean win, boolean processed) {
        this.player = player;
        this.outcome = outcome;
        this.amount = amount;
        this.currency = currency;
        this.timestampCreated = timestampCreated;
        this.win = win;
        this.processed = processed;
    }

    public Wager() {

    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
