package pl.dmcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dmcs.domain.AppUser;
import pl.dmcs.service.tempEnvironmentVars;

@Controller
public class SpringSecurityCustomPagesController {
    
    @RequestMapping(value = "/login")
    public String customLogin(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "activated", required = false) String activated,
                              Model model) {
        if (error != null) {
            if(error.equals("invalidToken")) {
                model.addAttribute("error", "Invalid token!");
                return "login";
            }
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        if (activated != null) {
            model.addAttribute("msg", "Account activated!");
        }

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "success", required = false) String success,
                              Model model) {
        model.addAttribute("captchaSiteKey", tempEnvironmentVars.SITE_KEY);
        model.addAttribute("appUser", new AppUser());

        if (error != null) {
            model.addAttribute("error", "Could not register user");
        }

        if (success != null) {
            model.addAttribute("msg", "Registered successfully, activate your account by clicking link sent in the email");
        }

        return "register";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}


