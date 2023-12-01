package com.epam.training.sportsbetting.mvc.homepage.transformer;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.mvc.homepage.model.PlayerModel;
import com.epam.training.sportsbetting.mvc.homepage.model.WagerSummary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HomepageModelTransformer {

    public Player transformPlayerModelToPlayer(PlayerModel playerModel) {
        Player playerResult = new Player();
        playerResult.setEmail(playerModel.getEmail());
        playerResult.setPassword(playerModel.getPassword());
        playerResult.setName(playerModel.getName());
        playerResult.setBalance(playerModel.getBalance());
        playerResult.setCurrency(Currency.transformStringToCurrency(playerModel.getCurrency()));
        playerResult.setVersion(playerModel.getVersion());
        return playerResult;
    }

    public PlayerModel transformPlayerToPlayerModel(Player player) {
        PlayerModel playerResult = new PlayerModel();
        playerResult.setName(player.getName());
        playerResult.setBalance(player.getBalance());
        playerResult.setCurrency(playerResult.getCurrency());
        playerResult.setVersion(playerResult.getVersion());
        return playerResult;
    }

    public Player setPlayerFieldsForDatabase(Player player, PlayerModel playerModel) {
        player.setName(playerModel.getName());
        player.setBalance(player.getBalance().add(playerModel.getBalance()));
        player.setCurrency(Currency.transformStringToCurrency(playerModel.getCurrency()));
        player.setVersion(playerModel.getVersion());
        return player;
    }

    public List<WagerSummary> transformWagersToWagerSummaries(List<Wager> wagers) {
        List<WagerSummary> wagerListResult = new ArrayList<>();
        for (Wager wager : wagers) {
            wagerListResult.add(transformWagerForWagerSummary(wager));
        }
        return wagerListResult;
    }

    public WagerSummary transformWagerForWagerSummary(Wager wager) {
        WagerSummary wagerResult = new WagerSummary();
        wagerResult.setWagerId(wager.getId());
        wagerResult.setEventTitle(wager.getOutcome().getBet().getSportEventTitle() + " - " + wager.getOutcome().getBet().getSportEventDate());
        if (wager.getOutcome().getBet().getEvent().getClass().getName().equals(FootballSportEvent.class.getName())) {
            wagerResult.setEventType("Football Match");
        } else {
            wagerResult.setEventType("Tennis Match");
        }
        wagerResult.setBetDescription(wager.getOutcome().getBet().getDescription());
        wagerResult.setOutcomeDescription(wager.getOutcome().getDescription());
        wagerResult.setOutcomeOdd(wager.getOutcome().getOdd());
        wagerResult.setWagerAmount("$ " + wager.getAmount() + " " + wager.getCurrency());
        if (!wager.isProcessed()) {
            wagerResult.setWagerWin("-");
            wagerResult.setWagerProcessed("-");
        } else if (wager.isWin()) {
            wagerResult.setWagerWin("Yes");
            wagerResult.setWagerProcessed("Yes");
        } else {
            wagerResult.setWagerWin("No");
            wagerResult.setWagerProcessed("Yes");
        }
        return wagerResult;
    }
}
