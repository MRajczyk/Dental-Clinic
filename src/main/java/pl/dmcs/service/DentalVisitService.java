package pl.dmcs.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.dmcs.domain.DentalVisit;

import java.util.List;

public interface DentalVisitService {
    @Secured("ROLE_ADMIN")
    void addDentalVisit(DentalVisit dentalVisit);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void editDentalVisit(DentalVisit dentalVisit);

    List<DentalVisit> listDentalVisits();

    List<DentalVisit> listPastDentalVisitsForPatient(long patientId);

    List<DentalVisit> listNotBookedDentalVisits();

    List<DentalVisit> listScheduledDentalVisitsForPatientId(long patientId);

    @Secured("ROLE_ADMIN")
    void removeDentalVisit (long id);

    DentalVisit getDentalVisit(long id);

    void cancelUnpaidDentalVisits();

    public boolean cancelVisit(long visitId, long patientId);

    public boolean payForVisit(long visitId, long patientId);

    public boolean scheduleVisit(long visitId, long userId);
}
