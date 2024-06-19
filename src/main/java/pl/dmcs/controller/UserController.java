package pl.dmcs.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.domain.AppUser;
import pl.dmcs.service.AppUserService;
import pl.dmcs.validator.AppUserDetailsValidator;

import java.util.Locale;

@Controller
public class UserController {

    private final AppUserService appUserService;
    private final AppUserDetailsValidator appUserValidator = new AppUserDetailsValidator();

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/user")
    public String showUserProfile(Locale locale, Model model, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        AppUser appUser = appUserService.getAppUser(userId);
        appUser.setPassword("");
        model.addAttribute("appUser", appUser);

        return "user";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String editAppUser(@ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model,
                              HttpServletRequest request, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        System.out.println("UpdateUser - First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() +
                " Tel.: " + appUser.getTelephone() +
                " Email: " + appUser.getEmail() +
                " Role: " + appUser.getRole() +
                " Password: " + appUser.getPassword());
        appUserValidator.validate(appUser, result);
        if(result.hasErrors() || appUser.getId() != userId) {
            model.addAttribute("appUser", appUser);
            model.addAttribute("error", "Error updating data");
            System.out.println(result.getAllErrors());
            return "user";
        }

        appUser.setRole("ROLE_USER");
        appUser.setEnabled(true);
        appUserService.editAppUser(appUser);
        model.addAttribute("success", "Data updated");
        model.addAttribute("appUser", appUser);
        return "user";
    }
}

