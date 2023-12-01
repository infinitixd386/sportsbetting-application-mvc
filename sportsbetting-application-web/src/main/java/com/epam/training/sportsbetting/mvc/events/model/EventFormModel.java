package com.epam.training.sportsbetting.mvc.events.model;

import java.util.List;

public class EventFormModel {

    private List<EventModelSummary> events;

    public EventFormModel(List<EventModelSummary> events) {
        this.events = events;
    }

    public List<EventModelSummary> getEvents() {
        return events;
    }
}
