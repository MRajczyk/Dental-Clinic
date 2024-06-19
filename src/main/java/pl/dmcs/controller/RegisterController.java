package pl.dmcs.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.domain.AppUser;
import pl.dmcs.service.*;
import pl.dmcs.validator.RegisterAppUserValidator;

@Controller
public class RegisterController {

    private final AppUserService appUserService;
    private final EmailService emailService;
    private final ActivationTokenService activationTokenService;
    private final RegisterAppUserValidator registerAppUserValidator = new RegisterAppUserValidator();

    @Autowired
    public RegisterController(AppUserService appUserService, EmailService emailService, ActivationTokenService activationTokenService) {
        this.appUserService = appUserService;
        this.emailService = emailService;
        this.activationTokenService = activationTokenService;
    }

    @Autowired
    public void setReCaptchaService(ReCaptchaService reCaptchaService) {
        this.reCaptchaService = reCaptchaService;
    }

    ReCaptchaService reCaptchaService;

    @RequestMapping(value = "/registerAppUser", method = RequestMethod.POST)
    public String registerAppUser(@ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model,
                             HttpServletRequest request) {
        appUser.setEnabled(false);
        appUser.setRole("ROLE_USER");
        model.addAttribute("appUser", appUser);
        System.out.println("Register - First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() +
                " Tel.: " + appUser.getTelephone() +
                " Email: " + appUser.getEmail() +
                " Role: " + appUser.getRole());

        registerAppUserValidator.validate(appUser, result);
        if (result.hasErrors()) {
            model.addAttribute("captchaSiteKey", tempEnvironmentVars.SITE_KEY);
            return "register";
        }
        if (result.getErrorCount() == 0 && reCaptchaService.verify(request.getParameter("g-recaptcha-response")))  {
            appUserService.addAppUser(appUser);

            String token = activationTokenService.createActivationToken(appUser);
            String activationLink = request.getRequestURL().toString().replace(request.getServletPath(), "")
                    + "/activate?token=" + token;
            String emailSubject = "Account created - confirmation email";
            String emailContent = "Registration successfull, please activate your account by clicking the link: " + activationLink;
            emailService.sendMail(appUser.getEmail(), emailContent, emailSubject);

            return "redirect:register?success";
        }

        return "redirect:register?error";
    }

}
