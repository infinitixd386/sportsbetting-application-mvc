package com.epam.training.sportsbetting.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class DummyDataStore {
    private List<SportEvent> sportEvents;
    private List<Bet> bets;
    private List<Player> players;
    private List<Wager> wagers;

    public DummyDataStore() {
        init();
    }

    public void init() {
        this.players = setPlayers();
        this.sportEvents = setEvents();
        this.bets = setBets();
        this.wagers = new ArrayList<>();
    }

    public List<Player> setPlayers() {
        players = new ArrayList<>();
        players.add(new Player("peter_veres@epam.com", "peti-pwd", "Peter Veres", new BigDecimal(100), Currency.EUR));
        players.add(new Player("levente_kiraly@epam.com", "123", "Levente Kiraly", new BigDecimal(1000), Currency.HUF));
        return players;
    }

    public List<SportEvent> setEvents() {
        sportEvents = new ArrayList<>();
        sportEvents.add(new FootballSportEvent("Arsenal vs Chelsea",
                LocalDateTime.parse("2016-10-16 19:00:00",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        return sportEvents;
    }

    public List<Bet> setBets() {
        bets = new ArrayList<>();
        List<Outcome> outcomes1 = new ArrayList<>();
        List<Outcome> outcomes2 = new ArrayList<>();
        List<Outcome> outcomes3 = new ArrayList<>();

        addBetList1(outcomes1);
        addBetList2(outcomes2);
        addBetList3(outcomes3);

        return bets;
    }

    private void addBetList1(List<Outcome> outcomes1) {
        outcomes1.add(new Outcome("1", null, new BigDecimal(2), null));
        outcomes1.add(new Outcome("2", null, new BigDecimal(4), null));
        Bet bet1 = new Bet(sportEvents.get(0), "player Oliver Giroud score", outcomes1);
        outcomes1.get(0).setBet(bet1);
        outcomes1.get(1).setBet(bet1);
        bets.add(bet1);
    }

    private void addBetList2(List<Outcome> outcomes2) {
        outcomes2.add(new Outcome("3", null, new BigDecimal(3), null));
        Bet bet2 = new Bet(sportEvents.get(0), "number of scored goals", outcomes2);
        outcomes2.get(0).setBet(bet2);
        bets.add(bet2);
    }

    private void addBetList3(List<Outcome> outcomes3) {
        outcomes3.add(new Outcome("Arsenal", null, new BigDecimal(2), null));
        outcomes3.add(new Outcome("Chelsea", null, new BigDecimal(3), null));
        Bet bet3 = new Bet(sportEvents.get(0), "winner", outcomes3);
        outcomes3.get(0).setBet(bet3);
        outcomes3.get(1).setBet(bet3);
        bets.add(bet3);
    }

    public void setWagers(List<Wager> wagers) {
        this.wagers = wagers;
    }

    public Wager createDataStoreWager(Player player, Outcome outcome, BigDecimal amount){
        LocalDateTime dateTime = LocalDateTime.now();
        Wager wager = new Wager(player, outcome, amount, player.getCurrency(), dateTime, false, false);
        wagers.add(wager);
        return wager;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public List<SportEvent> getSportEvents() {
        return sportEvents;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Wager> getWagers() {
        return wagers;
    }
}
