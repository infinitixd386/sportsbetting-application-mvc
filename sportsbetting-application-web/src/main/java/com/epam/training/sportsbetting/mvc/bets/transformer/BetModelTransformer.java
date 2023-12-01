package com.epam.training.sportsbetting.mvc.bets.transformer;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.mvc.bets.model.BetModelSummary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BetModelTransformer {

    public List<BetModelSummary> transformBetToBetModelSummaries(List<Bet> bets) {
        List<BetModelSummary> betListResult = new ArrayList<>();
        for (Bet bet : bets) {
            betListResult.add(transformBetToBetModelSummary(bet));
        }
        return betListResult;
    }

    public BetModelSummary transformBetToBetModelSummary(Bet bet) {
        BetModelSummary result = new BetModelSummary();
        result.setBetId(bet.getId());
        result.setBetDescription(bet.getDescription());
        return result;
    }

}
