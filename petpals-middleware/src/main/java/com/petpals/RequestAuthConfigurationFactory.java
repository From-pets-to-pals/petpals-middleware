package com.petpals;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class RequestAuthConfigurationFactory implements ClientRequestFilter {
	private final Logger logger = Logger.getLogger(RequestAuthConfigurationFactory.class.getName());
	@ConfigProperty(name = "caregivers.api.key")
	public String caregiversApiKey;
	@Context
	ContainerRequestContext requestContext;
		
		/**
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders, MultivaluedMap<String, String> clientOutgoingHeaders) {
		MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
		logger.info(incomingHeaders.toString());
		logger.info(uriInfo.toString());
		if (!uriInfo.getPath().equals("/login")) {
			if (incomingHeaders.get(HttpHeaders.AUTHORIZATION).isEmpty()) {
				result.add(HttpHeaders.AUTHORIZATION, incomingHeaders.get(HttpHeaders.AUTHORIZATION).get(0));
				result.add("API-KEY", caregiversApiKey);
			}
			logger.info(result.toString());
			
		}
		return result;
	}*/
	
	@Override
	public void filter(ClientRequestContext clientRequestContext) throws IOException {
		var incomingHeaders = clientRequestContext.getHeaders();
		logger.info(clientRequestContext.toString());
			clientRequestContext.getHeaders().add(HttpHeaders.AUTHORIZATION,
												  "looooo");
			clientRequestContext.getHeaders().add("API-KEY", caregiversApiKey);
		logger.info(clientRequestContext.getHeaders().toString());
			
	}
}
