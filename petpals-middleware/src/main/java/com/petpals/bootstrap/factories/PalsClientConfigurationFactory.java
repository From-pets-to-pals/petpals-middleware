package com.petpals.bootstrap.factories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@ApplicationScoped
public class PalsClientConfigurationFactory implements ClientHeadersFactory {
	@ConfigProperty(name = "pals.api.key")
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
