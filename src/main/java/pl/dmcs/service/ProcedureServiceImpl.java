package pl.dmcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.domain.Procedure;
import pl.dmcs.repository.ProcedureRepository;

import java.util.List;

@Service("procedureService")
public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository appUserRoleRepository) {
        this.procedureRepository = appUserRoleRepository;
    }

    @Override
    public void addProcedure(Procedure appUserRole) {
        procedureRepository.save(appUserRole);
    }

    @Override
    public List<Procedure> listProcedures() {
        return procedureRepository.findAll();
    }

    @Override
    public Procedure getProcedureById(long id) {
        return procedureRepository.getProcedureById(id);
    }
}
