package com.petpals.bootstrap.interceptors;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigValue;
import io.smallrye.config.Priorities;
import jakarta.annotation.Priority;

import java.util.Objects;

@Priority(Priorities.APPLICATION + 200)
public class TestInterceptor implements ConfigSourceInterceptor {
	private static final long serialVersionUID = 367246512037404779L;
	private final String VAULT = "azure.vault.url";
	private final String PALS_API_KEY = "pals.api.key";
	private final String CAREGIVERS_API_KEY = "caregivers.api.key";
	
	@Override
	public ConfigValue getValue(final ConfigSourceInterceptorContext context, final String name) {
		ConfigValue configValue = context.proceed(name);
		if (configValue == null) {
				if (context.proceed(VAULT).getValue() != null && !Objects.equals(
						context.proceed(VAULT).getValue(),
						context.proceed(VAULT).getRawValue()
				) ) {
					if (name.equals(PALS_API_KEY) || name.equals(CAREGIVERS_API_KEY)) {
							configValue = retrieveSecretFromAzure(context, name);
					}else if(name.equals("claims.origin") || name.equals("mp.jwt.verify.issuer")){
						String clientId = context.proceed("azure.clientid").getValue();
						String tenantId = context.proceed("azure.tenantid").getValue();
						String secret = context.proceed("azure.tenant.token").getValue();
						String vaultUrl = context.proceed(VAULT).getValue();
						SecretClient secretClient = new SecretClientBuilder()
															.vaultUrl(vaultUrl)
															.credential(new ClientSecretCredentialBuilder()
																				.tenantId(tenantId)
																				.clientSecret(secret)
																				.clientId(clientId)
																				.build())
															.buildClient();
						return
								ConfigValue.builder().withName(name).withValue(secretClient.getSecret(
										name.equals("claims.origin") ?"CLAIMS-ORIGIN":
												"TOKEN-ISSUER").getValue()).build();
					}
				
				}
		}
		return configValue;
	}
	
	private ConfigValue retrieveSecretFromAzure(ConfigSourceInterceptorContext context, String name) {
		String clientId = context.proceed("azure.clientid").getValue();
		String tenantId = context.proceed("azure.tenantid").getValue();
		String secret = context.proceed("azure.tenant.token").getValue();
		String vaultUrl = context.proceed(VAULT).getValue();
		SecretClient secretClient = new SecretClientBuilder()
											.vaultUrl(vaultUrl)
											.credential(new ClientSecretCredentialBuilder()
																.tenantId(tenantId)
																.clientSecret(secret)
																.clientId(clientId)
																.build())
											.buildClient();
		return
				ConfigValue.builder().withName(name).withValue(secretClient.getSecret(
						name.equals(PALS_API_KEY) ? "PALS-API-KEY" : "CAREGIVERS-API-KEY"
				).getValue()).build();
	}
	
}
