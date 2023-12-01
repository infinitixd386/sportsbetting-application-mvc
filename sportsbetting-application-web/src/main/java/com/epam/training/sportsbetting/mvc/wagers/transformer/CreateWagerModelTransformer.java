package com.epam.training.sportsbetting.mvc.wagers.transformer;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.mvc.wagers.model.CreateWagerModelSummary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateWagerModelTransformer {

    public List<CreateWagerModelSummary> transformOutcomeToOutcomeModelSummaries(List<Outcome> outcomes) {
        List<CreateWagerModelSummary> outcomeListResult = new ArrayList<>();
        for (Outcome outcome : outcomes) {
            outcomeListResult.add(transformOutcomeToCreateWagerModelSummary(outcome));
        }
        return outcomeListResult;
    }

    public CreateWagerModelSummary transformOutcomeToCreateWagerModelSummary(Outcome outcome) {
        CreateWagerModelSummary outcomeResult = new CreateWagerModelSummary();
        outcomeResult.setOutcomeId(outcome.getId());
        outcomeResult.setOutcomeDescription(outcome.getDescription());
        outcomeResult.setOutcomeOdd(outcome.getOdd());
        if (outcome.getWin()) {
            outcomeResult.setOutcomeWin("Win");
        } else {
            outcomeResult.setOutcomeWin("Lose");
        }
        return outcomeResult;
    }

    public Outcome transformCreateWagerModelSummaryToOutcome(CreateWagerModelSummary wagerModelSummary, String eventTitle,
                                                             String eventStartdate, String eventType, String betDescription) {
        SportEvent sportEventResult;
        Bet betResult = new Bet();
        Outcome outcomeResult = new Outcome();

        if (eventType.equals("FootballSportEvent")) {
            sportEventResult = new FootballSportEvent();
        } else {
            sportEventResult = new TennisSportEvent();
        }
        sportEventResult.setTitle(eventTitle);
        sportEventResult.setStartDate(LocalDateTime.parse(eventStartdate));
        betResult.setEvent(sportEventResult);
        betResult.setDescription(betDescription);
        outcomeResult.setBet(betResult);
        outcomeResult.setDescription(wagerModelSummary.getOutcomeDescription());
        outcomeResult.setOdd(wagerModelSummary.getOutcomeOdd());
        if (wagerModelSummary.getOutcomeWin().equals("Win")) {
            outcomeResult.setWin(true);
        } else {
            outcomeResult.setWin(false);
        }
        List<Outcome> outcomeList = new ArrayList<>();
        outcomeList.add(outcomeResult);
        betResult.setOutcomes(outcomeList);
        return outcomeResult;
    }
}
