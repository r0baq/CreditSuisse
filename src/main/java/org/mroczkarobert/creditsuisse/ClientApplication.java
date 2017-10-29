package org.mroczkarobert.creditsuisse;

import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ClientApplication {

	private static final String URL = "http://localhost:8080/validate";
	
	private static RestTemplate rest = new RestTemplate();
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Request body must be provided as execution parametr");
			return;
		}
		
		String body = String.join(" ", args);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		
		System.out.printf("Calling server: %s, %s\n", URL, body);
		ErrorTrade[] result = rest.postForObject(URL, entity, ErrorTrade[].class);
		System.out.printf("Response from external API: %s\n\n", (Object[]) result);
		
		for (ErrorTrade error : result) {
			System.out.printf("Trade: %s\n", error.getTrade());
			System.out.printf("Errors: %s\n\n", error.getErrors());
		}
	}
}
