package pl.dmcs.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.domain.AppUser;
import pl.dmcs.service.*;
import pl.dmcs.validator.AppUserValidator;
import pl.dmcs.validator.RegisterAppUserValidator;

import java.util.Arrays;

@Controller
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {
        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1);
        if (appUserId > 0) {
            AppUser appUser = appUserService.getAppUser(appUserId);
            appUser.setPassword("");
            model.addAttribute("appUser", appUser);
        }
        else {
            model.addAttribute("appUser", new AppUser());
        }
        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("appUserRoleList", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));

        return "appUsers";
    }

    @RequestMapping(value = "/editAppUser", method = RequestMethod.POST)
    public String editAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model,
                             HttpServletRequest request) {
        System.out.println("Edit - First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() +
                " Tel.: " + appUser.getTelephone() +
                " Email: " + appUser.getEmail() +
                " Role: " + appUser.getRole());

        appUserValidator.validate(appUser, result);
        System.out.println(result.getAllErrors());
        if(result.hasErrors()) {
            model.addAttribute("appUserList", appUserService.listAppUser());
            model.addAttribute("appUserRoleList", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
            return "appUsers";
        }
        appUserService.editAppUser(appUser);
        model.addAttribute("appUserList", appUserService.listAppUser());

        return "redirect:/appUsers?appUserId=" + appUser.getId();
    }

    @RequestMapping(value = "/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        appUserService.removeAppUser(appUserId);

        return "redirect:/appUsers";
    }
}
