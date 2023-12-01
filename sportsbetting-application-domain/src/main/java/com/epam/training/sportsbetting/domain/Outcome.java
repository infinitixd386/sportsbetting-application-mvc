package com.epam.training.sportsbetting.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Bet bet;
    private BigDecimal odd;
    private Boolean win;

    public Outcome(String description, Bet bet, BigDecimal odd, Boolean win) {
        this.description = description;
        this.bet = bet;
        this.odd = odd;
        this.win = win;
    }

    public Outcome() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public BigDecimal getOdd() {
        return odd;
    }

    public void setOdd(BigDecimal odd) {
        this.odd = odd;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public String getMatch() {
        return getBet().getSportEventTitle();
    }

}
