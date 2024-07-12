package com.petpals.bootstrap.interceptors;

import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class RestClientExceptionMapper implements ResponseExceptionMapper<PetPalsExceptions> {

    @Override
    public PetPalsExceptions toThrowable(Response response) {
        try {
            response.bufferEntity();
        } catch (Exception ignored) {
        }
        String msg = getBody(response);
        throw new PetPalsExceptions(ExceptionsEnum.findByMessage(msg));
    }

    private String getBody(Response response) {
        ByteArrayInputStream is = (ByteArrayInputStream) response.getEntity();
        byte[] bytes = new byte[is.available()];
        is.read(bytes, 0, is.available());
        String body = new String(bytes);
        return body;
    }
}