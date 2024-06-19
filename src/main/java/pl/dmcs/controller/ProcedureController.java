package pl.dmcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.domain.Procedure;
import pl.dmcs.service.ProcedureService;

@Controller
public class ProcedureController {
    private final ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @RequestMapping(value="/procedures")
    public String showProcedures(Model model) {
        model.addAttribute("procedure", new Procedure());
        model.addAttribute("procedures", procedureService.listProcedures());
        return "procedures";
    }

    @RequestMapping(value = "/addProcedure", method = RequestMethod.POST)
    public String addProcedure(@ModelAttribute("appUserRole") Procedure procedure, BindingResult result) {
        procedureService.addProcedure(procedure);
        return "redirect:procedures";
    }
}
