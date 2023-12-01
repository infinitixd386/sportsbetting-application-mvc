package com.epam.training.sportsbetting.mvc.wagers.controller;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.mvc.bets.model.BetModelSummary;
import com.epam.training.sportsbetting.mvc.bets.model.ShowBetRequest;
import com.epam.training.sportsbetting.mvc.bets.transformer.BetModelTransformer;
import com.epam.training.sportsbetting.mvc.events.model.EventModelSummary;
import com.epam.training.sportsbetting.mvc.events.model.ShowEventRequest;
import com.epam.training.sportsbetting.mvc.events.transformer.EventModelTransformer;
import com.epam.training.sportsbetting.mvc.wagers.model.CreateWagerFormModel;
import com.epam.training.sportsbetting.mvc.wagers.model.CreateWagerModelSummary;
import com.epam.training.sportsbetting.mvc.wagers.model.WagerModel;
import com.epam.training.sportsbetting.mvc.wagers.transformer.CreateWagerModelTransformer;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.EventRepository;
import com.epam.training.sportsbetting.repository.OutcomeRepository;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WagerController {


    public static final String REQUEST_MAPPING = "/events/bets/wagers";

    @Autowired
    public CreateWagerModelTransformer wagerModelTransformer;

    @Autowired
    public PlayerRepository playerRepository;

    @Autowired
    public BetRepository betRepository;

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public OutcomeRepository outcomeRepository;

    @Autowired
    public BetModelTransformer betModelTransformer;

    @Autowired
    public EventModelTransformer eventModelTransformer;

    @ModelAttribute("outcomeModelList")
    public CreateWagerFormModel createBetModelSummary(Authentication authentication, ShowEventRequest eventRequest, ShowBetRequest betRequest) {
        Player player = playerRepository.findByEmail(authentication.getName()).get();
        EventModelSummary eventModelSummary = eventModelTransformer.transformSportEventToEventModelSummary(eventRepository.findById(eventRequest.getEventId()).get());
        BetModelSummary betModelSummary = betModelTransformer.transformBetToBetModelSummary(betRepository.findById(betRequest.getBetId()).get());
        List<Outcome> outcomes = outcomeRepository.findAll();
        List<CreateWagerModelSummary> wagerSummaryList = wagerModelTransformer.transformOutcomeToOutcomeModelSummaries(outcomes);
        return new CreateWagerFormModel(wagerSummaryList, eventRequest.getEventId(), eventModelSummary.getEventTitle(), eventModelSummary.getEventStartdate(), eventModelSummary.getEventType(),betRequest.getBetId(), betModelSummary.getBetDescription(), player.getCurrency().toString());
    }

    @ModelAttribute("wagerModel")
    public WagerModel createFormModel(){
        return new WagerModel();
    }

    @RequestMapping(value = REQUEST_MAPPING, params = {"eventId","betId"})
    private String showCreateWagerPage() {
        String result;
        if (false) {
            result = "redirect:bets";
        } else {
            result = "wagers";
        }
        return result;
    }

}
