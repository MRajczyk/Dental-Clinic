package pl.dmcs.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import pl.dmcs.domain.DentalVisit;
import pl.dmcs.domain.Procedure;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ReceiptPdfServiceImpl implements ReceiptPdfService {

    @Override
    public boolean generateReceiptPdf(DentalVisit dentalVisit, HttpServletResponse response, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if(dentalVisit.getPatientId() != userId) {
            return false;
        }
        try {
            OutputStream o = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + "Receipt for visit_" + dentalVisit.getId() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o);
            pdf.open();
            pdf.add(new Paragraph("Receipt for Dental Appointment"));
            pdf.add(new Paragraph("Dentist: " + dentalVisit.getDentist()));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(2);
            table.addCell("Procedure name");
            table.addCell("Procedure cost");
            Float aggregatedCost = 0F;
            for(Procedure procedure: dentalVisit.getProcedures()) {
                table.addCell(procedure.getProcedureName());
                table.addCell(procedure.getCost().toString());
                aggregatedCost += procedure.getCost();
            }
            table.addCell("Total cost:");
            table.addCell(aggregatedCost.toString());
            pdf.add(table);
            pdf.close();
            o.close();
        }catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return true;
    }
}
