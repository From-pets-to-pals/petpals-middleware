package com.petpals.bootstrap;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

@OpenAPIDefinition(
		info = @Info(
				title = "PetPals",
				version = "0.1.0",
				description = "Petpals app for handling caregivers and their needs",
				license = @License(name = "Apache 2.0", url = "https://foo.bar"),
				contact = @Contact(name = "Sid", email = "sa.bennaceur@gmail.com")
		)
)
@SecuritySchemes({
				@SecurityScheme(
						scheme ="apiKey",
						securitySchemeName = "API-KEY",
						type = SecuritySchemeType.APIKEY,
						apiKeyName = "API-KEY",
						in = SecuritySchemeIn.HEADER
				),
				@SecurityScheme(
						scheme ="bearer",
						description = "jwt",
						securitySchemeName = "something something",
						type = SecuritySchemeType.HTTP,
						bearerFormat = "jwt",
						in = SecuritySchemeIn.DEFAULT
				)
				
		}
)
public class Swagger extends Application {
}
