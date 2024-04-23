package com.petpals.bootstrap;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class MyCredentialProvider {
	
	
	private static final Map<String, String> configuration = new HashMap<>();
	@ConfigProperty(name = "azure.tenantid")
	String tenantId;
	
	@ConfigProperty(name = "azure.tenant.token")
	String secret;
	
	@ConfigProperty(name = "azure.clientid")
	String clientId;
	
	void onStart(@Observes StartupEvent ev) {
		
		final String KEY_VAULT_URI = "https://petpals-key-vault.vault.azure.net";
		Logger.getAnonymousLogger().info(clientId + ": " + tenantId + ": " + secret);
		SecretClient secretClient = new SecretClientBuilder()
											.vaultUrl(KEY_VAULT_URI)
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
	
	
