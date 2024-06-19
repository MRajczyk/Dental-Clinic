package pl.dmcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.repository.DentalVisitRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DentalVisitServiceImpl implements DentalVisitService {

    private final DentalVisitRepository dentalVisitRepository;

    @Autowired
    public DentalVisitServiceImpl(DentalVisitRepository dentalVisitRepository) {
        this.dentalVisitRepository = dentalVisitRepository;
    }

    @Transactional
    @Override
    public void addDentalVisit(DentalVisit dentalVisit) {
        dentalVisitRepository.save(dentalVisit);
    }

    @Transactional
    @Override
    public void editDentalVisit(DentalVisit dentalVisit) {
        dentalVisitRepository.save(dentalVisit);
    }

    @Transactional
    @Override
    public List<DentalVisit> listDentalVisits() {
        return dentalVisitRepository.findAll();
    }

    @Transactional
    @Override
    public void removeDentalVisit(long id) {
        dentalVisitRepository.deleteById(id);
    }

    @Transactional
    @Override
    public DentalVisit getDentalVisit(long id) {
        return dentalVisitRepository.findById(id);
    }

    @Transactional
    @Override
    public boolean scheduleVisit(long visitId, long userId) {
        DentalVisit dentalVisit = this.dentalVisitRepository.findById(visitId);
        if(dentalVisit == null || dentalVisit.isBooked()) {
            return false;
        }

        dentalVisit.setBooked(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        dentalVisit.setBookedDateTime(parsedDateTime);
        dentalVisit.setPaid(false);
        dentalVisit.setPatientId(userId);

        this.dentalVisitRepository.save(dentalVisit);
        return true;
    }

    @Transactional
    @Override
    public boolean cancelVisit(long visitId, long patientId) {
        DentalVisit dentalVisit = this.dentalVisitRepository.findById(visitId);
        if(dentalVisit == null || dentalVisit.getPatientId() != patientId) {
            return false;
        }

        dentalVisit.setBooked(false);
        dentalVisit.setBookedDateTime(null);
        dentalVisit.setPaid(false);
        dentalVisit.setPatientId(null);

        this.dentalVisitRepository.save(dentalVisit);
        return true;
    }

    @Transactional
    @Override
    public boolean payForVisit(long visitId, long patientId) {
        DentalVisit dentalVisit = this.dentalVisitRepository.findById(visitId);
        if(dentalVisit == null || dentalVisit.isPaid() || dentalVisit.getPatientId() != patientId) {
            return false;
        }

        dentalVisit.setPaid(true);

        this.dentalVisitRepository.save(dentalVisit);
        return true;
    }

    @Override
    @Transactional
    public List<DentalVisit> listNotBookedDentalVisits() {
        return this.dentalVisitRepository.findByBookedIsFalse();
    }

    @Override
    public List<DentalVisit> listScheduledDentalVisitsForPatientId(long patientId) {
        return this.dentalVisitRepository.findByPatientIdAndAppointmentDateTimeAfter(patientId, LocalDateTime.now());
    }

    @Override
    public List<DentalVisit> listPastDentalVisitsForPatient(long patientId) {
        return this.dentalVisitRepository.findByAppointmentDateTimeBeforeAndPatientId(LocalDateTime.now(), patientId);
    }

    @Override
    @Scheduled(cron = "0 */2 * * * *") // Wykonaj co 2 minuty
    @Transactional
    public void cancelUnpaidDentalVisits() {
        LocalDateTime timeLimit = LocalDateTime.now().minusMinutes(5);
        List<DentalVisit> visitsToCancel = dentalVisitRepository.findByBookedDateTimeBeforeAndPaidIsFalse(timeLimit);

        for (DentalVisit visit : visitsToCancel) {
            visit.setBooked(false);
            visit.setBookedDateTime(null);
            visit.setPaid(false);
            visit.setPatientId(null);
        }

        dentalVisitRepository.saveAll(visitsToCancel);
    }
}
