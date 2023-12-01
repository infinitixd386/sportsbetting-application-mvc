package com.epam.training.sportsbetting.mvc.homepage.controller;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.mvc.homepage.transformer.HomepageModelTransformer;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.security.PlayerDetailService;
import com.epam.training.sportsbetting.security.PlayerPrincipal;
import com.epam.training.sportsbetting.service.DefaultSportsBettingService;
import com.epam.training.sportsbetting.mvc.homepage.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class PlayerSaveController {

    public static final String REQUEST_MAPPING = "/saveProfile.html";

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private HomepageModelTransformer modelTransformer;

    @Autowired
    private DefaultSportsBettingService sportsBettingService;

    @Autowired
    private PlayerDetailService detailService;

    @RequestMapping(value = REQUEST_MAPPING)
    private String savePlayer(@Valid PlayerModel playerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        String email = detailService.loadUserEmail();
        if (bindingResult.hasErrors()) {
            result = "homepage";
        } else {
            playerModel.setEmail(email);
            sportsBettingService.checkIfUserCanBeModified(modelTransformer.transformPlayerModelToPlayer(playerModel));
            Player player = modelTransformer.setPlayerFieldsForDatabase(playerRepository.findByEmail(email).get(), playerModel);
            playerRepository.save(player);
            redirectAttributes.addFlashAttribute("message", "Your information is Successfully saved!");
            result = "redirect:homepage.html";
        }
        return result;
    }

}
