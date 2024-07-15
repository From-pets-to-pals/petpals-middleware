package com.petpals.clients.services.pals;

import com.petpals.clients.dto.caregivers.AuthCaregiver;
import com.petpals.clients.endpoints.caregivers.AuthCaregiversClient;
import com.petpals.clients.mappers.caregivers.AuthCaregiverMapper;
import com.petpals.domain.commands.caregivers.AuthCaregiverCommand;
import com.petpals.domain.ports.in.JwtTokenGeneratorPort;
import com.petpals.domain.ports.out.AuthCaregiverOut;
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
public class AuthCaregiversService implements AuthCaregiverOut {
    private static final Logger LOGGER = Logger.getLogger(AuthCaregiversService.class);
    AuthCaregiversClient authCaregiversClient;
    AuthCaregiverMapper authCaregiverMapper;

    JwtTokenGenerator tokenGenerator;

    public AuthCaregiversService(@RestClient AuthCaregiversClient authCaregiversClient, AuthCaregiverMapper authCaregiverMapper, JwtTokenGenerator tokenGenerator) {
        this.authCaregiversClient = authCaregiversClient;
        this.authCaregiverMapper = authCaregiverMapper;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public String authCaregivers(AuthCaregiverCommand authCaregiverCommand) {
        AuthCaregiver authCaregiver = authCaregiverMapper.fromDomain(authCaregiverCommand);
        LOGGER.info("Sending auth caregiver request to Caregivers");
        LOGGER.info(authCaregiver.toString());
        try {
            authCaregiversClient.authCaregiver(authCaregiver);
            return tokenGenerator.getToken(authCaregiver.email(), String.valueOf(PalsFriendsTypes.CAREGIVER));

        } catch (ResteasyWebApplicationException e) {
            Response response = e.getResponse();
            String errorMessage = response.readEntity(String.class);
            throw new PetPalsExceptions(ExceptionsEnum.CAREGIVERS_WRONG_CREDENTIALS);
        }


    }
}
