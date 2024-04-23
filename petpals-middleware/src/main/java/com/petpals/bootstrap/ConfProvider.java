package com.petpals.bootstrap;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.Map;

public class ConfProvider {
	private static final Map<String, String> configuration = new HashMap<>();
	
	@ConfigProperty(name = "azure.tenantid")
	String tenantId;
	
	@ConfigProperty(name = "azure.tenant.token")
	String secret;
	
	@ConfigProperty(name = "azure.clientid")
	String clientId;
	
	@ConfigProperty(name = "azure.vault.url")
	String vaultUrl;
	
	void onStart(@Observes StartupEvent ev) {
		SecretClient secretClient = new SecretClientBuilder()
											.vaultUrl(vaultUrl)
											.credential(new ClientSecretCredentialBuilder()
																.tenantId(tenantId)
																.clientSecret(secret)
																.clientId(clientId)
																.build())
											.buildClient();
		configuration.put("caregivers.api.key", secretClient.getSecret("CAREGIVERS-API-KEY").getValue());
		configuration.put("pals.api.key", secretClient.getSecret("PALS-API-KEY").getValue());
		
	}
	
	public static String getValue(String s) {
		return configuration.get(s);
	}
	
}
	
	
