package com.petpals.bootstrap.interceptors;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.http.HttpStatus;

@Provider
public class JacksonExceptionHandler implements ExceptionMapper<UnrecognizedPropertyException> {
	@Override
	public Response toResponse(UnrecognizedPropertyException e) {
		return Response.status(Response.Status.fromStatusCode(HttpStatus.SC_BAD_REQUEST)).entity(e.getMessage()).build();
	}
}
