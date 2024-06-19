package pl.dmcs.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.service.DentalVisitService;

import java.util.List;
import java.util.Locale;

@Controller
public class ScheduleVisitController {

    private final DentalVisitService dentalVisitService;

    @Autowired
    public ScheduleVisitController(DentalVisitService dentalVisitService) {
        this.dentalVisitService = dentalVisitService;
    }

    @RequestMapping(value = "/scheduleVisit")
    public String showScheduleVisit(Locale locale, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        List<DentalVisit> dentalVisits = dentalVisitService.listNotBookedDentalVisits();
        model.addAttribute("visits", dentalVisits);

        return "scheduleVisit";
    }

    @RequestMapping(value = "/scheduleVisit/{visitId}")
    public String scheduleVisit(@PathVariable("visitId") Long visitId, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if(this.dentalVisitService.scheduleVisit(visitId, userId)) {
            List<DentalVisit> dentalVisits = dentalVisitService.listNotBookedDentalVisits();
            model.addAttribute("visits", dentalVisits);
            model.addAttribute("msg", "Successfully booked visit");
        } else {
            model.addAttribute("error", "Failed to book a visit");
        }
        return "scheduleVisit";
    }
}
