package com.epam.training.sportsbetting.mvc.homepage.controller;

import com.epam.training.sportsbetting.mvc.homepage.model.RemoveWagerRequest;
import com.epam.training.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RemoveWagerController {

    public static final String REQUEST_MAPPING = "/homepage";

    @Autowired
    public WagerRepository wagerRepository;

    @RequestMapping(value = REQUEST_MAPPING, params = "removeId")
    private String showHomepage(RemoveWagerRequest removeWagerRequest, RedirectAttributes redirectAttributes) {
        wagerRepository.delete(wagerRepository.getById(removeWagerRequest.getRemoveId()));
        redirectAttributes.addFlashAttribute("wagerMessage", "Your wager has been successfully removed!");
        return "redirect:homepage.html";
    }

}
