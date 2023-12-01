package com.epam.training.sportsbetting.mvc.homepage.controller;

import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.mvc.homepage.transformer.HomepageModelTransformer;
import com.epam.training.sportsbetting.mvc.homepage.model.PlayerModel;
import com.epam.training.sportsbetting.mvc.homepage.model.WagerFormModel;
import com.epam.training.sportsbetting.mvc.homepage.model.WagerSummary;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomepageController {

    public static final String REQUEST_MAPPING = "/homepage.html";

    @Autowired
    public WagerRepository wagerRepository;

    @Autowired
    public PlayerRepository playerRepository;

    @Autowired
    public HomepageModelTransformer modelTransformer;

    @ModelAttribute("playerModel")
    public PlayerModel createPlayerModel(Authentication authentication) {
        PlayerModel playerModel = modelTransformer.transformPlayerToPlayerModel(playerRepository.findByEmail(authentication.getName()).get());
        return playerModel;
    }

    @ModelAttribute("wagerModelList")
    public WagerFormModel createWagerModel() {
        List<Wager> wagers = wagerRepository.findByPlayer(playerRepository.findByEmail("levente_kiraly@epam.com").get());
        List<WagerSummary> wagerSummaryList = new ArrayList<>();
        if (!wagers.isEmpty()) {
            wagerSummaryList = modelTransformer.transformWagersToWagerSummaries(wagers);
        }
        return new WagerFormModel(wagerSummaryList);
    }

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    private String showHomepage() {
        return "homepage";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String showRootPage() {
        return "homepage";
    }
}
