package pl.dmcs.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.dmcs.domain.Procedure;
import pl.dmcs.service.ProcedureService;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProcedureListConverter implements Converter<String[], Set<Procedure>> {

    private final ProcedureService procedureService;

    public ProcedureListConverter(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Override
    public Set<Procedure> convert(String[] source) {
        System.out.println("Uruchamia się konwerter id procedur listy na procedury...");
        Set<Procedure> procedures = new HashSet<>();
        for (int i = 0; i < source.length; i++) {
            procedures.add(procedureService.getProcedureById(Long.parseLong(source[i])));
        }
        return procedures;
    }
}