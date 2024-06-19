package pl.dmcs.service;

import pl.dmcs.domain.Procedure;

import java.util.List;

public interface ProcedureService {
    void addProcedure(Procedure appUserRole);
    List<Procedure> listProcedures();
    Procedure getProcedureById(long id);
}
