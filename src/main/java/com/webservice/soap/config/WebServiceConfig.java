package com.webservice.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webservice.soap.CustomerOrdersWsImpl;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;

	@Bean
	public Endpoint endPoint() {

		Endpoint endPoint = new EndpointImpl(bus, new CustomerOrdersWsImpl());
		endPoint.publish("/customerOrder");

		return endPoint;
	}
}
