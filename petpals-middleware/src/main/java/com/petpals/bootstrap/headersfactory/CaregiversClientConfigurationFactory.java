package com.petpals.bootstrap.headersfactory;

import com.petpals.bootstrap.MyCredentialProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@ApplicationScoped
public class CaregiversClientConfigurationFactory implements ClientHeadersFactory {
	
	@Override
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders, MultivaluedMap<String, String> clientOutgoingHeaders) {
		var apiKey = MyCredentialProvider.getValue("caregivers.api.key");
		MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
		if (incomingHeaders.get(HttpHeaders.AUTHORIZATION) != null) {
			result.add(HttpHeaders.AUTHORIZATION, incomingHeaders.get(HttpHeaders.AUTHORIZATION).get(0));
		}
		result.add("API-KEY", apiKey);
		return result;
	}
	
}
