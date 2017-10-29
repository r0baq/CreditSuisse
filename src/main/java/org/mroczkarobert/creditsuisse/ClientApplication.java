package org.mroczkarobert.creditsuisse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.mroczkarobert.creditsuisse.transport.ValidationResult;
import org.mroczkarobert.creditsuisse.util.ClientErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ClientApplication {

	private static final String URL = "http://localhost:8080/validate";
	
	public static void main(String[] args) throws IOException {
		RestTemplate rest = new RestTemplate();
		rest.setErrorHandler(new ClientErrorHandler());
		
		String body;
		
		if (args.length == 0) {
			body = new String(Files.readAllBytes(Paths.get("SampleTestData.txt")), Charset.forName("UTF-8"));
			
		} else {
			body = String.join(" ", args);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		
		System.out.printf("Calling server: %s, %s\n", URL, body);
		ValidationResult[] result = rest.postForObject(URL, entity, ValidationResult[].class);
		System.out.printf("Response from external API: %s\n\n", (Object[]) result);

		for (ValidationResult error : result) {
			System.out.printf("Trade: %s\n", error.getTrade());
			System.out.printf("Errors: %s\n\n", error.getErrors());
		}
	}
}
