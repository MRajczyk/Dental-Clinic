package pl.dmcs.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.domain.AppUser;
import pl.dmcs.service.AppUserService;
import pl.dmcs.validator.AppUserDetailsValidator;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HelloController {

    private final AppUserService appUserService;

    @Autowired
    public HelloController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/")
    public String helloWorld(Locale locale, Model model, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }
        AppUser appUser = appUserService.getAppUser(userId);
        model.addAttribute("login", appUser.getLogin());
        return "hello";
    }
}
