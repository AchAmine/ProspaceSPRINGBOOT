package com.prospace.spring.service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Response;
import com.prospace.spring.entity.ResultQuizz;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PartnerRepository;
import com.prospace.spring.repository.QuizzRepository;
import com.prospace.spring.repository.ResponseRepository;
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
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/";

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
	@Autowired
	ResponseRepository responseRepository;
	
	 
	    private void writeTableHeader(PdfPTable table) {
	    	      
	    }
	      
	     
/*	    private void writeTableData(PdfPTable table) {
	        for (Partner user : partnerRepository.findAll()) {
	            table.addCell(String.valueOf(user.getUser().getIdUser()));

	          
	        }
	    }*/
@Override
    public void export(HttpServletResponse response,Long quizzId,Long userId) {
    Quizz q= quizzRepository.findById(quizzId).orElse(null);
    User u = userRepository.findById(userId).orElse(null);
    PdfPTable table = new PdfPTable(1);
    	Document document = new Document(PageSize.A3);
        try {
			PdfWriter.getInstance(document, response.getOutputStream());

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
        document.open();
      
        try {
			
			 Image Logo = Image.getInstance("./src/main/resources/prospacelogo.PNG");
	    	  Logo.scalePercent(3);
	    	  Logo.setWidthPercentage(20);
	    	  Logo.scaleAbsolute(80f, 80f);
	    	  Logo.setAlignment(Image.ALIGN_CENTER);
			
		      
		  //    PdfPTable table1 = new PdfPTable(3);
		 //     PdfPCell cell = new PdfPCell();

        Font font = new Font();
        font.setColor(Color.BLUE);
        font.setSize(30);
        font.setStyle(Font.SYMBOL);
        
       
      if(  resultQuizzRepository.getResultbyquizzanduser(q, u).getScore()<80){
    	  
        Paragraph p = new Paragraph("QUIZZ RESULT", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f});
        table.setSpacingBefore(10);
    
       
        table.addCell("USER:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getUser().getFirstName()+
        		" "+resultQuizzRepository.getResultbyquizzanduser(q, u).getUser().getLastName()));
        
        table.addCell("SCORE:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getScore()));
        table.addCell("PASSING SCORE: 80");

        table.addCell("TOTAL QUESTIONS:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getNbreQuestions()));

        table.addCell("FALSE ANSWERS:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getFalseAnswers()));
        table.addCell("TRUE ANSWERS:"+String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getCorrectAnswers()));

     
            table.addCell("you did not pass");

        
      }else {
    	  
    	  Image img = Image.getInstance(QR_CODE_IMAGE_PATH+u.getUsername().toString()+".png");
			img.scalePercent(3);
			img.setWidthPercentage(10);
			img.scaleAbsolute(120f, 120f);
			img.setAlignment(Image.ALIGN_CENTER);
          Font font1 = new Font();
          font1.setColor(Color.BLACK);
          font1.setStyle(Font.SYMBOL);
          font1.setSize(20);
    	  Paragraph p = new Paragraph("CERTIFICATE",font);
    	  Paragraph p1 = new Paragraph("THIS IS TO CERTIFY THAT MR./MRS. ",font1);
    	  Paragraph p2 = new Paragraph(String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getUser().getFirstName())+" "+
    			  String.valueOf(resultQuizzRepository.getResultbyquizzanduser(q, u).getUser().getLastName()),font1 );
    	  Paragraph p3 = new Paragraph("HAD PARTICIPATED THE QUIZ COMPETITION ",font1);
    	  /*Paragraph p4 = new Paragraph("by "+String.valueOf(q.getPartner()),font1);
    	  Paragraph p4 = new Paragraph("BY "+q.getPartner().getFirstName().toString()+" "+
    			  q.getPartner().getLastName().toString(),font1);*/
    	  Paragraph p4 = new Paragraph("WE APPRECIATE YOUR EFFORT ,KEEP PARTICIPATING !",font1)  ;
    	  p.setAlignment(Paragraph.ALIGN_CENTER);
    	  p1.setAlignment(Paragraph.ALIGN_CENTER);
    	  p2.setAlignment(Paragraph.ALIGN_CENTER);
    	  p3.setAlignment(Paragraph.ALIGN_CENTER);
    	  p4.setAlignment(Paragraph.ALIGN_CENTER);
    	  document.add(Logo);
    	
            	
    	 
          document.add(p);
          document.add(p1);
          document.add(p2);
          document.add(p3);
          document.add(p4);
          document.add(img);

      }
        
       // Quizz q = quizzRepository.findById(quizzId).orElse(null);
      //  User u= userRepository.findById(userId).orElse(null);
      
      
       	
       // writeTableHeader(table);
       // writeTableData(table);
       

        document.add(table);
        
         
        document.close();
        } catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


}