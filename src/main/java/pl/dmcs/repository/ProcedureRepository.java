package pl.dmcs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.domain.Procedure;

import java.util.Optional;

@Transactional
@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    Procedure findByProcedureName(String procedureName);

    Procedure getProcedureById(Long id);
}
