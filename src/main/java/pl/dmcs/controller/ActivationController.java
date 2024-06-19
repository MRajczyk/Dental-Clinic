package pl.dmcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dmcs.service.AppUserService;

@Controller
public class ActivationController {

    private final AppUserService appUserService;

    @Autowired
    public ActivationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Transactional
    @GetMapping("/activate")
    public String activateAccount(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {

        if(appUserService.activateUser(token)) {
            return "redirect:/login?activated";
        } else {
            return "redirect:/login?error=invalidToken";
        }
    }
}