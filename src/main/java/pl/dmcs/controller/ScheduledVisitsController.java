package pl.dmcs.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.service.DentalVisitService;
import pl.dmcs.service.ReceiptPdfService;

import java.util.List;
import java.util.Locale;

@Controller
public class ScheduledVisitsController {

    private final DentalVisitService dentalVisitService;
    private final ReceiptPdfService receiptPdfService;

    @Autowired
    public ScheduledVisitsController(DentalVisitService dentalVisitService, ReceiptPdfService receiptPdfService) {
        this.dentalVisitService = dentalVisitService;
        this.receiptPdfService = receiptPdfService;
    }

    @RequestMapping(value = "/scheduledVisits")
    public String showScheduledVisits(Locale locale, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        List<DentalVisit> dentalVisits = dentalVisitService.listScheduledDentalVisitsForPatientId(userId);
        model.addAttribute("visits", dentalVisits);

        return "scheduledVisits";
    }

    @RequestMapping(value = "/cancelVisit/{visitId}")
    public String cancelVisit(@PathVariable("visitId") Long visitId, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if(this.dentalVisitService.cancelVisit(visitId, userId)) {
            model.addAttribute("msg", "Successfully cancelled an appointment");
        } else {
            model.addAttribute("error", "Failed to cancel an appointment");
        }
        List<DentalVisit> dentalVisits = dentalVisitService.listScheduledDentalVisitsForPatientId(userId);
        model.addAttribute("visits", dentalVisits);
        return "scheduledVisits";
    }

    @RequestMapping(value = "/payForVisit/{visitId}")
    public String payForVisit(@PathVariable("visitId") Long visitId, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if(this.dentalVisitService.payForVisit(visitId, userId)) {
            model.addAttribute("msg", "Successfully paid for an appointment");
        } else {
            model.addAttribute("error", "Failed to pay for an appointment");
        }
        List<DentalVisit> dentalVisits = dentalVisitService.listScheduledDentalVisitsForPatientId(userId);
        model.addAttribute("visits", dentalVisits);
        return "scheduledVisits";
    }

    @RequestMapping(value = "/printReceipt/{visitId}")
    public String printReceipt(@PathVariable("visitId") Long visitId, Model model, HttpServletResponse response, HttpSession session) {
        if (!this.receiptPdfService.generateReceiptPdf(this.dentalVisitService.getDentalVisit(visitId), response, session)) {
            return "redirect:/accessDenied";
        }

        return null;
    }
}
