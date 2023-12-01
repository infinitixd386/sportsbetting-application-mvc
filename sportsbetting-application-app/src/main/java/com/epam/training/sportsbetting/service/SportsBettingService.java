package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.domain.Wager;

public interface SportsBettingService {

    Player authenticateUser(User user);
    Player findPlayerByEmail(String email);
    List<Bet> findAllBets();
    Wager createWager(Player player, Outcome outcome, BigDecimal amount);
    void cancel(Wager wager);
    List<Wager> findAllWagers();
    void calculateResults(List<Wager> wagers);
}
