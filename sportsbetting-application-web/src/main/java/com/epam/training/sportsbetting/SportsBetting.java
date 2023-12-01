package com.epam.training.sportsbetting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.service.LowBalanceException;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.view.View;
import org.springframework.stereotype.Component;

@Component
public class SportsBetting {

    private static final Logger LOG = LoggerFactory.getLogger(SportsBetting.class);

    private View playerView;
    private SportsBettingService playerService;

    @Autowired
    public SportsBetting(View playerView, SportsBettingService playerService) {
        this.playerView = playerView;
        this.playerService = playerService;
    }

    public void play() {

        Player player;
        player = playerService.authenticateUser(playerView.readCredentials());
        playerView.printWelcomeMessage(player);

        betCycle(playerView, playerService, player);
        playerService.calculateResults(playerService.findAllWagers());
        playerView.printResults(player, playerService.findAllWagers());
    }

    @SuppressWarnings("checkstyle:MethodLength")
    private static void betCycle(View playerView, SportsBettingService playerService, Player player) {
        boolean bettingCycle = true;
        Wager playerWager = null;
        while (bettingCycle) {
            playerView.printOutcome(playerService.findAllBets());
            Outcome selectedOutcome = playerView.selectOutcome(playerService.findAllBets());
            if (!(selectedOutcome == null)) {
                while (playerWager == null) {
                    try {
                        playerWager = playerService.createWager(player, selectedOutcome, playerView.requestAmountForBet());
                    } catch (LowBalanceException ex) {
                        playerView.printLowBalanceMessage(player);
                    }
                }
                playerView.printWagerSaved(playerWager);
                playerWager = null;
            } else {
                bettingCycle = false;
            }
        }
    }

}
