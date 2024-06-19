package pl.dmcs.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.dmcs.domain.AppUser;
import pl.dmcs.domain.DentalVisit;

public interface ReceiptPdfService {
    public boolean generateReceiptPdf(DentalVisit dentalVisit, HttpServletResponse response, HttpSession session);
}
