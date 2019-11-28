package com.dit.batchtest.batch;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class BatchWriter implements ItemWriter<String> {
	
  	private String outputLocation;
	
	public BatchWriter(String outputLocation) {
		this.outputLocation = outputLocation;
	}

	@Override
	public void write(List<? extends String> messages) throws Exception {
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(outputLocation+"/output.txt", true));
	    for (String msg : messages) {
	    	System.out.println("Writing the data " + msg);
	        pw.println(msg);
	    }
	    pw.close();
	}

}
