package com.petpals.bootstrap.factories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CaregiversClientConfigurationFactory implements ClientHeadersFactory {
	private final Logger logger = Logger.getLogger(CaregiversClientConfigurationFactory.class);
	@ConfigProperty(name = "caregivers.api.key")
	String apiKey;
	
	@Override
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders, MultivaluedMap<String, String> clientOutgoingHeaders) {
		MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
		if (incomingHeaders.get(HttpHeaders.AUTHORIZATION) != null) {
			result.add(HttpHeaders.AUTHORIZATION, incomingHeaders.get(HttpHeaders.AUTHORIZATION).get(0));
		}
		if(!clientOutgoingHeaders.containsKey("API-KEY")){
			result.add("API-KEY", apiKey);
		}
		return result;
	}
	
}
