package com.epam.training.sportsbetting.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("2")
public class TennisSportEvent extends SportEvent {

    public TennisSportEvent(String title, LocalDateTime startdate) {
        super(title, startdate);
    }

    public TennisSportEvent() {
    }
}
