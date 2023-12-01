package com.epam.training.sportsbetting.mvc.homepage.model;

import java.util.List;

public class WagerFormModel {

    private List<WagerSummary> playersWagers;

    public WagerFormModel(List<WagerSummary> playersWagers) {
        this.playersWagers = playersWagers;
    }

    public List<WagerSummary> getPlayersWagers() {
        return playersWagers;
    }
}
