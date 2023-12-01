package com.epam.training.sportsbetting.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("1")
public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startdate) {
        super(title, startdate);
    }

    public FootballSportEvent() {
    }
}
