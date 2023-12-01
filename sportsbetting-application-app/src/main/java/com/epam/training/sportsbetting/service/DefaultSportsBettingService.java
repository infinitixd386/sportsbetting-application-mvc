package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class DefaultSportsBettingService implements SportsBettingService {

    private BetRepository betRepository;
    private PlayerRepository playerRepository;
    private WagerRepository wagerRepository;

    @Autowired
    public DefaultSportsBettingService(BetRepository betRepository, PlayerRepository playerRepository, WagerRepository wagerRepository) {
        this.betRepository = betRepository;
        this.playerRepository = playerRepository;
        this.wagerRepository = wagerRepository;
    }


    @Override
    public Player authenticateUser(User user) {
        Optional<Player> optionalPlayer = playerRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return optionalPlayer.orElseThrow(() -> new AuthenticationException("No such Player Found."));
    }

    public Player findPlayerByEmail(String email) {
        Optional<Player> optionalPlayer = playerRepository.findByEmail(email);
        return optionalPlayer.orElseThrow(() -> new AuthenticationException("No such Player Found."));
    }

    public void checkIfUserCanBeModified(Player player) {
        Optional<Player> modifiedPlayer = playerRepository.findByEmail(player.getEmail());
        if(player.getVersion()!=modifiedPlayer.get().getVersion()){
            throw new IllegalStateException("Concurrent modification.");
        }
    }

    @Override
    public List<Bet> findAllBets() {
        return betRepository.findAll();
    }

    @Override
    public Wager createWager(Player player, Outcome outcome, BigDecimal amount) {
        int lessThen = -1;
        if (comparePlayerBalanceAndBetAmount(player, amount) == lessThen) {
            throw new LowBalanceException();
        }
        return createDataStoreWager(player, outcome, amount);
    }

    private Wager createDataStoreWager(Player player, Outcome outcome, BigDecimal amount) {
        LocalDateTime dateTime = LocalDateTime.now();
        Wager wager = new Wager(player, outcome, amount, player.getCurrency(), dateTime, false, false);
        payingForCreatedWager(player, amount);
        wagerRepository.save(wager);
        return wager;
    }

    private void payingForCreatedWager(Player player, BigDecimal amount) {
        player.setBalance(player.getBalance().subtract(amount));
        playerRepository.save(player);
    }

    private int comparePlayerBalanceAndBetAmount(Player player, BigDecimal amount) {
        return player.getBalance().compareTo(amount);
    }

    @Override
    public List<Wager> findAllWagers() {
        return wagerRepository.findAll();
    }

    @Override
    public void cancel(Wager wager){
        if(!wager.isProcessed()){
            wagerRepository.delete(wager);
        }
    }

    @Override
    public void calculateResults(List<Wager> wagers) {

        setWinningWagers(wagers);

        for (Wager wager : wagers) {
            wager.setProcessed(true);
            wagerRepository.save(wager);
            if (wager.isWin()) {
                payingPlayerForWinningWager(wager);
            }
        }
    }

    private void payingPlayerForWinningWager(Wager wager) {
        wager.getPlayer().setBalance(wager.getPlayer().getBalance()
                .add(multiplyBetAmountByOutcomeOdd(wager)));
    }

    private BigDecimal multiplyBetAmountByOutcomeOdd(Wager wager) {
        return wager.getAmount().multiply(wager.getOutcome().getOdd());
    }

    private void setWinningWagers(List<Wager> wagers) {
        if (wagers.size() == 1) {
            wagers.get(0).setWin(true);
        } else {
            wagers.get(1).setWin(true);
        }
    }
}
