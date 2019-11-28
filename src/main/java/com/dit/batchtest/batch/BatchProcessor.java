package com.dit.batchtest.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

public class BatchProcessor implements ItemProcessor<String, String> {
	
	@Value("${encode.shift}")
	private int shift;
	
	String encode(String message, int offset) {
		StringBuilder result = new StringBuilder();
			for (char character : message.toCharArray()) {
				if (character != ' ') {
					int originalAlphabetPosition = character - 'a';
					int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
					char newCharacter = (char) ('a' + newAlphabetPosition);
					result.append(newCharacter);
				} else {
					result.append(character);
				}
			}
		return result.toString();
	}

	@Override
	public String process(String data) throws Exception {
		return encode(data,shift);
	}

}
