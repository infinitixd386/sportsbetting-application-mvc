package com.epam.training.sportsbetting.view;

import java.math.BigDecimal;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.domain.Wager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleView implements View {

    private static final String ENTER_PLAYER_ADDRESS = "> Enter player email address:";
    private static final String ENTER_PLAYER_PASSWORD = "> Enter player password:";
    private static final String WELCOME_MESSAGE = "> Welcome %s\n> Your balance is %s %s";
    private static final String PRINT_OUTCOME_MESSAGE = "> %s: Sport event: %s (start: %s), Bet: %s, Outcome: %s, Actual odd: %s.";
    private static final String SELECT_BET = "> What do you want to bet on? (choose a number or press %s for quit)";
    private static final String EXIT_STRING = "q";
    private static final String ENTER_BET_AMOUNT = "> What amount do you wish to bet on it?";
    private static final String NOT_ENOUGH_MONEY = "You don't have enough money, your balance is %s%s";
    private static final String SAVED_WAGER = "> Wager %s=%s of %s [odd: %s, amount: %s] saved!";
    private static final String PLAYER_BALANCE = "> Your balance is %s %s";
    private static final String RESULTS_MESSAGE = "> Results:";
    private static final String RESULTS_OF_WAGER = "> Wager '%s=%s' of %s [odd: %s, amount: %s], win: %s";
    private static final String PLAYER_NEW_BALANCE = "> Your new balance is %s %s";

    private ConsoleIO console;

    @Autowired
    public ConsoleView(ConsoleIO console) {
        this.console = console;
    }

    @Override
    public User readCredentials() {
        console.println(ENTER_PLAYER_ADDRESS);
        String email = console.nextLine();
        console.println(ENTER_PLAYER_PASSWORD);
        String pwd = console.nextLine();
        return new User(email, pwd);
    }

    @Override
    public void printWelcomeMessage(Player player) {
        console.println(String.format(WELCOME_MESSAGE, player.getName(), player.getBalance(), player.getCurrency()));
    }

    @Override
    public void printOutcome(List<Bet> bets) {
        int index = 1;
        for (Bet bet : bets) {
            for (Outcome outcome : bet.getOutcomes()) {
                console.println(String.format(PRINT_OUTCOME_MESSAGE, index++, bet.getSportEventTitle(), bet.getSportEventDate()
                        , bet.getDescription(), outcome.getDescription(), outcome.getOdd()));
            }
        }
    }

    @Override
    public Outcome selectOutcome(List<Bet> bets) {
        String selected;
        Outcome selectedOutcome = null;
        console.println(String.format(SELECT_BET, EXIT_STRING));
        selected = console.nextLine();
        if (!(selected.equals(EXIT_STRING))) {
            selectedOutcome = getSelectedOutcome(bets, selected, selectedOutcome);
        }
        return selectedOutcome;
    }

    private Outcome getSelectedOutcome(List<Bet> bets, String selected, Outcome selectedOutcome) {
        int index = 1;
        int selectedIndex = Integer.parseInt(selected);
        for (Bet bet : bets) {
            for (Outcome outcome : bet.getOutcomes()) {
                if (index++ == selectedIndex) {
                    selectedOutcome = outcome;
                }
            }
        }
        return selectedOutcome;
    }

    @Override
    public BigDecimal requestAmountForBet() {
        console.println(ENTER_BET_AMOUNT);
        BigDecimal money = console.nextBigDecimal();
        return money;
    }

    @Override
    public void printLowBalanceMessage(Player player) {
        console.println(String.format(NOT_ENOUGH_MONEY, player.getBalance(), player.getCurrency()));
    }

    @Override
    public void printWagerSaved(Wager wager) {
        console.println(String.format(SAVED_WAGER, wager.getOutcome().getBet().getDescription(), wager.getOutcome().getDescription()
                , wager.getOutcome().getBet().getSportEventTitle(), wager.getOutcome().getOdd(), wager.getAmount()));

        console.println(String.format(PLAYER_BALANCE, wager.getPlayer().getBalance(), wager.getPlayer().getCurrency()));
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {
        console.println(RESULTS_MESSAGE);
        for (Wager wager : wagers) {
            console.println(String.format(RESULTS_OF_WAGER, wager.getOutcome().getBet().getDescription(), wager.getOutcome().getDescription()
                    , wager.getOutcome().getMatch(), wager.getOutcome().getOdd(), wager.getAmount(), wager.isWin()));
        }
        console.println(String.format(PLAYER_NEW_BALANCE, player.getBalance(), player.getCurrency()));

    }
}
