package com.dit.batchtest.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchJobInvokerController {

	@Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
    
    @Value("${file.path}")
  	private String filePath;
 
    @GetMapping("/invokejob")
    public String handle() throws Exception {
            JobParameters jobParameters = new JobParametersBuilder().addLong("id", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(processJob, jobParameters);
 
        return "Batch job has been invoked";
    }
}
