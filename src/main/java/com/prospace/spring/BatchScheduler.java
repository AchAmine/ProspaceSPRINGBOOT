package com.prospace.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class BatchScheduler {

	@Autowired
    private BatchLauncher batchLauncher;
    
   // @Scheduled(cron = "0 0 0 * * *") 
    @Scheduled(fixedRate = 300000)  
    public void perform() throws Exception {
    	
        batchLauncher.run();
        
    }
    
}
