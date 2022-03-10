package com.prospace.spring.configuration;

import java.io.File;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class AfterAspect {

	@AfterReturning("execution(* com.prospace.spring.BatchLauncher.*(..))")
	public void logMethodExit1(JoinPoint joinPoint) {
	log.info("!!!!!!!!!!!!!!!!!Deleting all csv files!!!!!!!!!!!!!!!!!!!! ");
	
	File folder = new File("C:\\Work\\workspace-sts\\prospace-project\\src\\main\\resources\\");
	File fList[] = folder.listFiles();
	for (int i = 0; i < fList.length; i++) {
	    String pes = fList[i].getName();
	    log.info("FILE NAME : "+pes);
	    if (pes.endsWith(".csv")) {
	    	log.info("FILE CSV");
	    	File file = new File("C:\\Work\\workspace-sts\\prospace-project\\src\\main\\resources\\"+pes);
	    	log.info("File name : "+file.getName());
	    	log.info("exist : "+file.exists());
	        boolean isDeleted = file.delete();
	        log.info("FILE DELETED"+isDeleted);
	    }
	}
	}
	
}
