package pl.dmcs.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.dmcs.domain.Procedure;
import pl.dmcs.service.ProcedureService;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProcedureConverter implements Converter<String, Set<Procedure>> {

    private final ProcedureService procedureService;

    public ProcedureConverter(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Override
    public Set<Procedure> convert(String source) {
        System.out.println("Uruchamia się konwerter pojedynczego id na procedurę...");
        Set<Procedure> procedures = new HashSet<Procedure>(0);
        procedures.add(procedureService.getProcedureById(Long.parseLong(source)));

        return procedures;
    }
}