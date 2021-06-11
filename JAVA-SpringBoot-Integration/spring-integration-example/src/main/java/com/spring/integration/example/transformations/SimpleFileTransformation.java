package com.spring.integration.example.transformations;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class SimpleFileTransformation {

	public String transform(String filePath) throws IOException 
	{

		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		return "Transformed: " + content;

	}//transform
	
	
	public String getBase64String(String filePath) throws IOException
	{
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		String encodedString = Base64.getEncoder().withoutPadding().encodeToString(content.getBytes());
		return encodedString;
	}
	
	
}
