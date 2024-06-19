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
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.service.*;

@Controller
public class DentalVisitController {

    ProcedureService procedureService;
    DentalVisitService dentalVisitService;
    @Autowired
    public DentalVisitController(ProcedureService procedureService, DentalVisitService dentalVisitService) {
        this.procedureService = procedureService;
        this.dentalVisitService = dentalVisitService;
    }

    @RequestMapping(value = "/visits")
    public String showVisits(Model model, HttpServletRequest request) {
        int visitId = ServletRequestUtils.getIntParameter(request, "visitId", -1);
        if (visitId > 0) {
            DentalVisit dentalVisit = dentalVisitService.getDentalVisit(visitId);
            model.addAttribute("dentalVisit", dentalVisit);
        }
        else {
            model.addAttribute("dentalVisit", new DentalVisit());
        }
        model.addAttribute("dentalVisitsList", this.dentalVisitService.listDentalVisits());
        model.addAttribute("proceduresList", this.procedureService.listProcedures());

        return "visits";
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public String addDentalVisit(@ModelAttribute("dentalVisit") DentalVisit dentalVisit, BindingResult result, Model model,
                             HttpServletRequest request) {
        //TODO: add validator
//        if (result.getErrorCount() == 0)  {}

        if (dentalVisit.getId() == 0) {
            dentalVisitService.addDentalVisit(dentalVisit);
            model.addAttribute("dentalVisitsList", this.dentalVisitService.listDentalVisits());
            return "redirect:visits";
        }
        else {
            dentalVisitService.editDentalVisit(dentalVisit);
            model.addAttribute("dentalVisitsList", this.dentalVisitService.listDentalVisits());
            model.addAttribute("dentalVisit", dentalVisit);
            return "redirect:/visits?visitId=" + dentalVisit.getId();
        }
    }

    @RequestMapping(value = "/deleteVisit/{visitId}")
    public String deleteDentalVisit(@PathVariable("visitId") Long dentalVisitId) {
        dentalVisitService.removeDentalVisit(dentalVisitId);

        return "redirect:/visits";
    }
}
