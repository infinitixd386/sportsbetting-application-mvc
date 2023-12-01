package com.epam.training.sportsbetting.mvc.events.controller;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.mvc.events.model.EventFormModel;
import com.epam.training.sportsbetting.mvc.events.model.EventModelSummary;
import com.epam.training.sportsbetting.mvc.events.transformer.EventModelTransformer;
import com.epam.training.sportsbetting.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EventController {

    public static final String REQUEST_MAPPING = "/events.html";

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public EventModelTransformer modelTransformer;

    @ModelAttribute("eventFormModel")
    public EventFormModel createEventModel() {
        List<SportEvent> events = eventRepository.findAll();
        List<EventModelSummary> eventSummaryList = modelTransformer.transformSportEventToEventModelSummaries(events);
        return new EventFormModel(eventSummaryList);
    }

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    private String showEvents() {
        return "events";
    }
}
