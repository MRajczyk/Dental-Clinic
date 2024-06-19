package pl.dmcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.domain.Procedure;
import pl.dmcs.repository.ProcedureRepository;

import java.util.List;

@RestController
@RequestMapping("proceduresRest")
public class ProceduresRESTController {

    private ProcedureRepository procedureRepository;

    @Autowired
    public ProceduresRESTController(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }
	
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Procedure> getProceduresInJSON() {
   	 return procedureRepository.findAll();
    }
 
    @RequestMapping(value = "/all.xml", method = RequestMethod.GET, produces = "application/xml")
    public List<Procedure> getProceduresIgetAppUserInXML() {
        return procedureRepository.findAll();
    }
}

