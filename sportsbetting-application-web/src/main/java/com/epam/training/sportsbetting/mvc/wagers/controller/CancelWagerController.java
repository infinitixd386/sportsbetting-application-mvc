package com.epam.training.sportsbetting.mvc.wagers.controller;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CancelWagerController {

    public static final String REQUEST_MAPPING = "/cancelWager.html";

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    public String cancelWager() {
        return "homepage";
    }

}
