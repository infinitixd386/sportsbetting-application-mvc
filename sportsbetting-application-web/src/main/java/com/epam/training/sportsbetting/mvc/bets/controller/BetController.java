package com.epam.training.sportsbetting.mvc.bets.controller;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.mvc.bets.model.BetModelSummary;
import com.epam.training.sportsbetting.mvc.bets.transformer.BetModelTransformer;
import com.epam.training.sportsbetting.mvc.events.model.EventModelSummary;
import com.epam.training.sportsbetting.mvc.events.model.ShowEventRequest;
import com.epam.training.sportsbetting.mvc.bets.model.BetFormModel;
import com.epam.training.sportsbetting.mvc.events.transformer.EventModelTransformer;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class BetController {

    public static final String REQUEST_MAPPING = "/events/bets";

    @Autowired
    public BetRepository betRepository;

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public BetModelTransformer betModelTransformer;

    @Autowired
    public EventModelTransformer eventModelTransformer;

    @ModelAttribute("betModelList")
    public BetFormModel createBetModelSummary(ShowEventRequest eventRequest) {
        EventModelSummary eventModelSummary = eventModelTransformer.transformSportEventToEventModelSummary(eventRepository.findById(eventRequest.getEventId()).get());
        List<Bet> bets = betRepository.findAll();
        List<BetModelSummary> betSummaryList = betModelTransformer.transformBetToBetModelSummaries(bets);
        return new BetFormModel(betSummaryList, eventRequest.getEventId(), eventModelSummary.getEventTitle(), eventModelSummary.getEventStartdate(), eventModelSummary.getEventType());
    }

    @RequestMapping(value = REQUEST_MAPPING, params = "eventId")
    private String listBets() {
        String result;
        if (false) {
            result = "redirect:events";
        } else {
            result = "bets";
        }
        return result;
    }
}
