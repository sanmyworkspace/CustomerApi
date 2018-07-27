package com.tring.customer.rest.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.tring.customer.rest.exception.CustomerResourceExceptionMapper;
import com.tring.customer.rest.exception.GenericCustomerExceptionMapper;
import com.tring.customer.rest.resource.CustomerManagementResource;
import com.tring.customer.swagger.config.CustomerApiSwaggerConfig;

/**
 * @author akula
 * 
 *         This is REST resources configuration class where all the resources
 *         will be registered with jersey container and further this is made a
 *         as bean in Spring container with @Component annotation.
 */
@Component
public class CustomerResourceConfig extends ResourceConfig {
	public CustomerResourceConfig() {
		register(CustomerManagementResource.class);
		register(CustomerResourceExceptionMapper.class);
		register(GenericCustomerExceptionMapper.class);
		register(JacksonFeature.class);
	}
}
