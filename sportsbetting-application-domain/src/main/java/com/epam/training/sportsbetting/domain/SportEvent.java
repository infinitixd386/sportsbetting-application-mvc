package com.epam.training.sportsbetting.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorColumn(name = "EVENT_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime startdate;

    public SportEvent(String title, LocalDateTime startdate) {
        this.title = title;
        this.startdate = startdate;
    }

    public SportEvent() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startdate;
    }

    public void setStartDate(LocalDateTime startdate) {
        this.startdate = startdate;
    }
}
