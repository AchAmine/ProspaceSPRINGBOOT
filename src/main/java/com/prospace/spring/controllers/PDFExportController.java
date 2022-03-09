package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prospace.spring.entity.QRCodeGenerator;
import com.prospace.spring.service.IServiceResultQuizz;
import com.prospace.spring.service.PDFGeneratorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;
    @Autowired
    IServiceResultQuizz resultQuizzServie;

    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate/{Quizz-id}/{User-id}")
    public void generatePDF(HttpServletResponse response,@PathVariable("Quizz-id")Long quizzId,@PathVariable("User-id")Long userId) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
      //  this.resultQuizzServie.calculScore(quizzId, userId);
        this.pdfGeneratorService.export(response,quizzId,userId);
    }
    
   
}