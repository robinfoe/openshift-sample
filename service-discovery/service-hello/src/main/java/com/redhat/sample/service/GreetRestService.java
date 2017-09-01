package com.redhat.sample.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greet")
public class GreetRestService {

	
	@RequestMapping(value = "/kube-dns/plain", method = RequestMethod.GET, produces = "text/plain")
	public String greeting(){
		
		return "hello world";
	}
	
}
