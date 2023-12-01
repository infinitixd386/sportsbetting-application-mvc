package com.epam.training.sportsbetting.mvc.wagers.controller;

import com.epam.training.sportsbetting.mvc.bets.model.BetModelSummary;
import com.epam.training.sportsbetting.mvc.bets.model.ShowBetRequest;
import com.epam.training.sportsbetting.mvc.bets.transformer.BetModelTransformer;
import com.epam.training.sportsbetting.mvc.events.model.EventModelSummary;
import com.epam.training.sportsbetting.mvc.events.model.ShowEventRequest;
import com.epam.training.sportsbetting.mvc.wagers.transformer.CreateWagerModelTransformer;
import com.epam.training.sportsbetting.mvc.events.transformer.EventModelTransformer;
import com.epam.training.sportsbetting.mvc.wagers.model.CreateWagerModelSummary;
import com.epam.training.sportsbetting.mvc.wagers.model.WagerModel;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.EventRepository;
import com.epam.training.sportsbetting.repository.OutcomeRepository;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.security.PlayerDetailService;
import com.epam.training.sportsbetting.service.SportsBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@SessionAttributes({"betRequest", "loginRequest"})
public class CreateWagerController {

    public static final String REQUEST_MAPPING = "/submitWager";

    @Autowired
    public SportsBettingService bettingService;

    @Autowired
    public OutcomeRepository outcomeRepository;

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public BetRepository betRepository;

    @Autowired
    public PlayerRepository playerRepository;

    @Autowired
    public CreateWagerModelTransformer wagerModelTransformer;

    @Autowired
    public EventModelTransformer eventModelTransformer;

    @Autowired
    public BetModelTransformer betModelTransformer;

    @Autowired
    public PlayerDetailService detailService;

    @RequestMapping(value = REQUEST_MAPPING)
    private ModelAndView createWagerFromModel(@Valid WagerModel wagerModel, ShowEventRequest eventRequest, ShowBetRequest betRequest,
                                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            result = "wagers";
        } else {
            CreateWagerModelSummary wagerModelSummary = wagerModelTransformer.transformOutcomeToCreateWagerModelSummary(outcomeRepository.getById(wagerModel.getOutcomeId()));
            EventModelSummary eventModelSummary = eventModelTransformer.transformSportEventToEventModelSummary(eventRepository.getById(eventRequest.getEventId()));
            BetModelSummary betModelSummary = betModelTransformer.transformBetToBetModelSummary(betRepository.getById(betRequest.getBetId()));
            BigDecimal amount = new BigDecimal(wagerModel.getWagerAmount());
            bettingService.createWager(playerRepository.findByEmail(detailService.loadUserEmail()).get(),
                    wagerModelTransformer.transformCreateWagerModelSummaryToOutcome(wagerModelSummary, eventModelSummary.getEventTitle(), eventModelSummary.getEventStartdate(), eventModelSummary.getEventType(), betModelSummary.getBetDescription()), amount);
            redirectAttributes.addFlashAttribute("wagerMessage", "Your wager is Successfully created!");
            result = "homepage.html";
        }
        return new ModelAndView("redirect:" + result);
    }

}
