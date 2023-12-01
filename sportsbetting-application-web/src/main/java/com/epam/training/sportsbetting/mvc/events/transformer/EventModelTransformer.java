package com.epam.training.sportsbetting.mvc.events.transformer;

import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.mvc.events.model.EventModelSummary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventModelTransformer {

    public List<EventModelSummary> transformSportEventToEventModelSummaries(List<SportEvent> events) {
        List<EventModelSummary> eventListResult = new ArrayList<>();
        for (SportEvent event : events) {
            eventListResult.add(transformSportEventToEventModelSummary(event));
        }
        return eventListResult;
    }

    public EventModelSummary transformSportEventToEventModelSummary(SportEvent sportEvent) {
        EventModelSummary result = new EventModelSummary();
        result.setEventId(sportEvent.getId());
        result.setEventTitle(sportEvent.getTitle());
        result.setEventStartdate(sportEvent.getStartDate().toString());
        if (sportEvent.getClass().getName().equals(FootballSportEvent.class.getName())) {
            result.setEventType("Football Match");
        } else {
            result.setEventType("Tennis Match");
        }
        return result;
    }

}
