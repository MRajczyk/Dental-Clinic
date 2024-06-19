package pl.dmcs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.domain.DentalVisit;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface DentalVisitRepository extends JpaRepository<DentalVisit, Long> {
    DentalVisit findById(long id);
    List<DentalVisit> findByBookedDateTimeBeforeAndPaidIsFalse(LocalDateTime timeLimit);
    List<DentalVisit> findByBookedIsFalse();
    List<DentalVisit> findByPatientIdAndAppointmentDateTimeAfter(long patientId, LocalDateTime time);
    List<DentalVisit> findByAppointmentDateTimeBeforeAndPatientId(LocalDateTime timeLimit, long patientId);
}
