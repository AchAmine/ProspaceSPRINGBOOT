package com.prospace.spring.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.PdfPTable;

public interface IServicePDFGenerator {
	
	 public void export(HttpServletResponse response,Long quizzId,Long userId) ;
}
