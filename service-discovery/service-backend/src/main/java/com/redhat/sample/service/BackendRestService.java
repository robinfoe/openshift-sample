package com.redhat.sample.service;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.sample.service.scalar.ServiceChainDTO;

@RestController
@RequestMapping("/api/backend")
public class BackendRestService {
	
	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public ServiceChainDTO greet(){
		ServiceChainDTO response = new ServiceChainDTO();
		response.setGreeting("From backend ");
		Date date = new Date();
		response.setTime(date.getTime());

		return response;
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain")
	public String test(){
		return "test hello";
	}

}
