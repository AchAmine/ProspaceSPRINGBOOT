package com.prospace.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.QRCodeGenerator;

@RestController
public class QRCodeController {
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/";

	
    @GetMapping(value = "/genrateAndDownloadQRCode/{username}/{codeText}")
		public void download(
				 String codeText,@PathVariable("username") String username
				)
			    throws Exception {
			        QRCodeGenerator.generateQRCodeImage(codeText, QR_CODE_IMAGE_PATH+username+".png");
			    }

    @GetMapping(value = "/genrateQRCode/{codeText}")
   	public ResponseEntity<byte[]> generateQRCode(
   			@PathVariable("codeText") String codeText)
   		    throws Exception {
   		        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText));
   		    }
}
