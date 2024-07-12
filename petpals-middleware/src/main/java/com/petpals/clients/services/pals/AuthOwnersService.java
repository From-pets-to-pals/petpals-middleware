package com.petpals.clients.services.pals;

import com.petpals.clients.dto.pals.AuthOwner;
import com.petpals.clients.endpoints.pals.AuthOwnersClient;
import com.petpals.clients.mappers.pals.AuthOwnerMapper;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import com.petpals.domain.ports.in.JwtTokenGeneratorPort;
import com.petpals.domain.ports.out.AuthOwnerOut;
import com.petpals.domain.services.JwtTokenGenerator;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class AuthOwnersService implements AuthOwnerOut {
    private static final Logger LOGGER = Logger.getLogger(AuthOwnersService.class);
    AuthOwnersClient authOwnersClient;
    AuthOwnerMapper authOwnerMapper;

    JwtTokenGenerator tokenGenerator;

    public AuthOwnersService(@RestClient AuthOwnersClient authOwnersClient, AuthOwnerMapper authOwnerMapper, JwtTokenGenerator tokenGenerator) {
        this.authOwnersClient = authOwnersClient;
        this.authOwnerMapper = authOwnerMapper;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public String authOwners(AuthOwnerCommand authOwnerCommand) {
        AuthOwner authOwner = authOwnerMapper.fromDomain(authOwnerCommand);
        LOGGER.info("Sending auth owner request to Pals");
        LOGGER.info(authOwner.toString());
        try {
            authOwnersClient.authOwner(authOwner);
            return tokenGenerator.getToken(authOwner.email(), String.valueOf(PalsFriendsTypes.OWNER));

        } catch (ResteasyWebApplicationException e) {
            Response response = e.getResponse();
            String errorMessage = response.readEntity(String.class);
            throw new PetPalsExceptions(ExceptionsEnum.OWNERS_WRONG_CREDENTIALS);
        }


    }
}
