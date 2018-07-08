package com.tring.customer.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Provider
public class GenericCustomerExceptionMapper implements ExceptionMapper<Throwable>{
	
	Logger logger=LogManager.getLogger(GenericCustomerExceptionMapper.class);
	
	@Override
	public Response toResponse(Throwable exception) {
		logger.error(exception);
		return Response
                .status(Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(exception.getLocalizedMessage())
                .build();
		
	}

}
