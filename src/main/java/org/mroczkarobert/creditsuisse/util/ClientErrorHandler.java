package org.mroczkarobert.creditsuisse.util;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ClientErrorHandler implements ResponseErrorHandler {
	
	  @Override
	  public void handleError(ClientHttpResponse response) throws IOException {
	  }

	  @Override
	  public boolean hasError(ClientHttpResponse response) throws IOException {
		  return !HttpStatus.OK.equals(response.getStatusCode()) && !HttpStatus.BAD_REQUEST.equals(response.getStatusCode());
	  }
	}
