package com.prospace.spring.service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.ResultQuizz;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PartnerRepository;
import com.prospace.spring.repository.QuizzRepository;
import com.prospace.spring.repository.ResultQuizzRepository;
import com.prospace.spring.repository.UserRepository;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 

@Service
public class PDFGeneratorService implements IServicePDFGenerator{
	@Autowired
	PartnerRepository partnerRepository;
	@Autowired
	QuizzRepository quizzRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IServiceResultQuizz serviceResultQuizz;
	@Autowired
	ResultQuizzRepository resultQuizzRepository;
	
	 
	    private void writeTableHeader(PdfPTable table) {
	    	      
	    }
	      
	     
/*	    private void writeTableData(PdfPTable table) {
	        for (Partner user : partnerRepository.findAll()) {
	            table.addCell(String.valueOf(user.getUser().getIdUser()));

	          
	        }
	    }*/
@Override
    public void export(HttpServletResponse response,Long quizzId,Long userId) {
    	
    	Document document = new Document(PageSize.A4);
        try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("QUIZZ RESULT", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
       // Quizz q = quizzRepository.findById(quizzId).orElse(null);
      //  User u= userRepository.findById(userId).orElse(null);
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f});
        table.setSpacingBefore(10);
        Quizz q= quizzRepository.findById(quizzId).orElse(null);
        User u = userRepository.findById(userId).orElse(null);
       
             	
        table.addCell("USER:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getUser().getFirstName()+
        		" "+resultQuizzRepository.getResultbyquizzanduser(q, u).getUser().getLastName()));
        
        table.addCell("SCORE:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getScore()));
       // table.addCell("TOTAL QUESTIONS:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getTotalQuestions()));

        table.addCell("FALSE QUESTIONS:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getFalseQuestions()));
        table.addCell("TRUE QUESTIONS:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getCorrectQuestions()));
        if(resultQuizzRepository.getResultbyquizzanduser(q, u).getScore()<80){
            table.addCell("TEST NON VALIDE, BONNE CONTINUATION");

        }else {
        	table.addCell(" FELICITAIONS !!VOUZ AVEZ REUSSI");
        }
       	
       // writeTableHeader(table);
       // writeTableData(table);
         
        document.add(table);
         
        document.close();
  
}}