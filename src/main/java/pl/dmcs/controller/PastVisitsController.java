package pl.dmcs.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.service.DentalVisitService;
import pl.dmcs.service.ReceiptPdfService;

import java.util.List;
import java.util.Locale;

@Controller
public class PastVisitsController {

    private final DentalVisitService dentalVisitService;

    @Autowired
    public PastVisitsController(DentalVisitService dentalVisitService) {
        this.dentalVisitService = dentalVisitService;
    }

    @RequestMapping(value = "/pastVisits")
    public String showPastVisits(Locale locale, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        List<DentalVisit> dentalVisits = dentalVisitService.listPastDentalVisitsForPatient(userId);
        model.addAttribute("visits", dentalVisits);

        return "pastVisits";
    }
}
