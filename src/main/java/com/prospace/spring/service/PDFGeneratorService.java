package com.prospace.spring.service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PartnerRepository;
import com.prospace.spring.repository.QuizzRepository;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 

@Service
public class PDFGeneratorService {
	@Autowired
	PartnerRepository partnerRepository;
	
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Partner ID", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Date Début Partenariat", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Date Fin Partenariat", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Partner Email", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Partner name", font));
	        table.addCell(cell);       
	    }
	     
	    private void writeTableData(PdfPTable table) {
	        for (Partner user : partnerRepository.findAll()) {
	            table.addCell(String.valueOf(user.getUser().getIdUser()));
	            table.addCell(String.valueOf(user.getDateDebutPartenariat()));
	            table.addCell(String.valueOf(user.getDateFinPartenariat()));
	            table.addCell(String.valueOf(user.getUser().getEmail()));
	            table.addCell(String.valueOf(user.getUser().getFirstName()+""+user.getUser().getLastName()));
	          
	        }
	    }

    public void export(HttpServletResponse response) throws IOException {
    	
    	Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Partners", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
  
}}