package com.redhat.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.redhat.sample.service.scalar.ServiceChainDTO;

@RestController
@RequestMapping("/api/greet")
public class GreetRestService {
	
	private final Logger log = LoggerFactory.getLogger(GreetRestService.class);
	
	@Autowired
	private Environment env;
	private RestTemplate restfulTemplate = new RestTemplate();

	
	
	@RequestMapping(value = "/kube-dns/plain", method = RequestMethod.GET, produces = "text/plain")
	public String greeting(){
		
		log.info("Stepping into Gretting");
		log.info("calling backend : " + this.env.getProperty("backend_chaincall_plain"));
		ServiceChainDTO response = restfulTemplate.getForObject(this.env.getProperty("backend_chaincall_plain"), ServiceChainDTO.class);
		return "hello world " +  response.getGreeting();
	}
	
}
