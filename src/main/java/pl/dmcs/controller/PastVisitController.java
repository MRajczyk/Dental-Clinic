package pl.dmcs.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.service.DentalVisitService;

import java.util.Locale;
import java.util.Objects;

@Controller
public class PastVisitController {

    private final DentalVisitService dentalVisitService;

    @Autowired
    public PastVisitController(DentalVisitService dentalVisitService) {
        this.dentalVisitService = dentalVisitService;
    }

    @RequestMapping(value = "/pastVisit/{visitId}")
    public String showPastVisit(@PathVariable("visitId") Long visitId, Locale locale, Model model, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        DentalVisit dentalVisit = dentalVisitService.getDentalVisit(visitId);
        if(dentalVisit == null) {
            model.addAttribute("error", "No visit with given Id found");
        } else if(!Objects.equals(dentalVisit.getPatientId(), userId)) {
            model.addAttribute("error", "Access denied");
        }
        model.addAttribute("visit", dentalVisit);

        return "pastVisit";
    }
}
