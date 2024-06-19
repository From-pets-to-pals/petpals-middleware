package com.petpals.clients.services.pals;

import com.petpals.clients.dto.pals.AuthOwner;
import com.petpals.clients.endpoints.pals.AuthOwnersClient;
import com.petpals.clients.mappers.pals.AuthOwnerMapper;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import com.petpals.domain.ports.out.AuthOwnerOut;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class AuthOwnersService implements AuthOwnerOut {
    private static final Logger LOGGER = Logger.getLogger(AuthOwnersService.class);
    AuthOwnersClient authOwnersClient;
    AuthOwnerMapper authOwnerMapper;

    public AuthOwnersService(@RestClient AuthOwnersClient authOwnersClient, AuthOwnerMapper authOwnerMapper) {
        this.authOwnersClient = authOwnersClient;
        this.authOwnerMapper = authOwnerMapper;
    }

    @Override
    public String authOwners(AuthOwnerCommand authOwnerCommand) {
        AuthOwner authOwner = authOwnerMapper.fromDomain(authOwnerCommand);
        try {
            LOGGER.info("Sending auth owner request to Pals" );
            LOGGER.info(authOwner.toString());
            return authOwnersClient.authOwner(authOwner);
        } catch (ResteasyWebApplicationException e) {
            LOGGER.info(e.toString());
            throw new PetPalsExceptions(
                    ExceptionsEnum.DB_UNIQUE_KEY_OWNER_MAIL_CONSTRAINT_VIOLATION
            );
        } catch (ResteasyClientErrorException e) {
            LOGGER.info(e.toString());
            throw new PetPalsExceptions(
                    ExceptionsEnum.PALS_MISSING_API_KEY);
        }
    }
}
