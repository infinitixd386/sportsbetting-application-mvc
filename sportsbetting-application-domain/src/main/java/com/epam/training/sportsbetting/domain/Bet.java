package com.epam.training.sportsbetting.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private SportEvent sportEvent;
    private String description;
    @OneToMany(mappedBy = "bet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Outcome> outcome;

    public Bet(SportEvent sportEvent, String description, List<Outcome> outcome) {
        this.sportEvent = sportEvent;
        this.description = description;
        this.outcome = outcome;
    }

    public Bet() {

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

    public SportEvent getEvent() {
        return sportEvent;
    }

    public void setEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public List<Outcome> getOutcomes() {
        return outcome;
    }

    public void setOutcomes(List<Outcome> outcome) {
        this.outcome = outcome;
    }

    public String getSportEventTitle() {
        return sportEvent.getTitle();
    }

    public LocalDateTime getSportEventDate() {
        return sportEvent.getStartDate();
    }

}
