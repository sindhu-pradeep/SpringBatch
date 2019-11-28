package com.dit.batchtest.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Value("${thread.count}")
  	private Integer threadcount;
	
	@Value("${file.path}")
  	private String filePath;
	
	@Value("${output.file.location}")
  	private String outputFileLocation;
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("SpringBatchJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<String, String> chunk(threadcount)
				.reader(new BatchReader(new FileSystemResource(filePath))).processor(new BatchProcessor())
				.writer(new BatchWriter(outputFileLocation)).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new BatchJobCompletionListener();
	}
}
