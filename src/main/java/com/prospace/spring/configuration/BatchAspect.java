package com.prospace.spring.configuration;

import java.io.File;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class BatchAspect {
	
	@AfterReturning("execution(* com.prospace.spring.BatchLauncher.*(..))")
	public void logMethodExit1(JoinPoint joinPoint) {
	log.info("!!!!!!!!!!!!!!!!!Deleting all csv files!!!!!!!!!!!!!!!!!!!! ");
	File file = new File("C:\\Work\\workspace-sts\\prospace-project\\src\\main\\resources\\*.csv");
		
	// File file = new File("*.csv");
	file.delete();
	}


}