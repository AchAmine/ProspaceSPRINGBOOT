package com.prospace.spring.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventTarget;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.prospace.spring.entity.Event;
import com.prospace.spring.repository.EventRepository;
@Service
public class PDFExporter {
	 @Autowired
     EventRepository r;
	private List<Event> listevents;
    
    public PDFExporter(List<Event> listevents) {
     
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(2);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("id_event", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("depenses", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("ends_at", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("location", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("price", font));
        table.addCell(cell);      
        
        cell.setPhrase(new Phrase("profit", font));
        table.addCell(cell);  
        
        cell.setPhrase(new Phrase("slot", font));
        table.addCell(cell);  
        
        cell.setPhrase(new Phrase("starts_at", font));
        table.addCell(cell);  
        
        cell.setPhrase(new Phrase("title", font));
        table.addCell(cell); 
        
        
    }
     
    private void writeTableData(PdfPTable table) {
        for (Event event : r.findAll()) {
            table.addCell(String.valueOf(event.getIdEvent()));
            table.addCell(String.valueOf(event.getDepenses()));
            table.addCell(String.valueOf(event.getEndsAt()));
            table.addCell((String.valueOf(event.getLocation()).toString()));
            table.addCell(String.valueOf(event.getProfit()));
            table.addCell(String.valueOf(event.getPrice()));
            table.addCell(String.valueOf(event.getSlot()));
            table.addCell(String.valueOf(event.getStartsAt()));
            table.addCell((String.valueOf(event.getTitle()).toString()));
            //table.addCell(event.getEndsAt());
          
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
         
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f,1.5f,1.5f,1.5f,1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
