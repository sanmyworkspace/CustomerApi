package com.tring.customer.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Provider
public class CustomerResourceExceptionMapper implements ExceptionMapper<CustomerResourceException>{
	
	Logger logger=LogManager.getLogger(CustomerResourceExceptionMapper.class);
	
	@Override
	public Response toResponse(CustomerResourceException ex) {
		logger.error(ex);
		ErrorMessage errorMessage=new ErrorMessage(ex.getErrorMessage(),ex.getErrorCode());
		return Response
                .status(errorMessage.getErrorCode())
                .type(MediaType.APPLICATION_JSON)
                .entity(errorMessage)
                .build();
	}

}
