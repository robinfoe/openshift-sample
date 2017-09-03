package com.redhat.sample.service.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class BankController {
	
	private static final int FALLBACK_SCORE = 500;

    @Value("${base.rate:10}")
    private float baseRate;

    @Value("${bank.name:thebank}")
    private String bankName;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/quote")
    @HystrixCommand(commandKey = "RequestScoreFromCreditBureau", fallbackMethod = "fallbackQuote", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public Quote quote(@RequestParam("ssn") Long ssn, @RequestParam("amount") Double amount, @RequestParam("duration") Integer duration) {
        Integer score = restTemplate.getForObject("http://loanbroker-credit-bureau/eval?ssn={ssn}", Integer.class, ssn);
        Double rate = baseRate + (double) (duration / 12) / 10 + (double) (1000 - score) / 1000;
        return new Quote(bankName, rate, amount, duration);
    }

    public Quote fallbackQuote(@RequestParam("ssn") Long ssn, @RequestParam("amount") Double amount, @RequestParam("duration") Integer duration) {
        Double rate = baseRate + (double) (duration / 12) / 10 + (double) (1000 - FALLBACK_SCORE) / 1000;
        return new Quote(bankName, rate, amount, duration);
    }

}
