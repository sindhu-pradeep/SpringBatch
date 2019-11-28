package com.dit.batchtest.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.Resource;

public class BatchReader extends FlatFileItemReader<String> {

	public BatchReader(Resource resource) {
		super();
		setResource(resource);
		
		setLineMapper(new CustomLineMapper());
	}
	
	public class CustomLineMapper implements LineMapper<String>{

	    @Override
	    public String mapLine(String line, int lineNumber) throws Exception {
	        return line;
	    }

	}

}

