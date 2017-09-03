package com.redhat.sample.service.bank;

public class Quote {
	
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private Double rate;
	public Double getRate() {return rate;}
    public void setRate(Double rate) {this.rate = rate;}

	private Double amount;
    public Double getAmount() {return amount;}
    public void setAmount(Double amount) {this.amount = amount;}

	private Integer duration;
    public Integer getDuration() {return duration;}
    public void setDuration(Integer duration) {this.duration = duration;}

    public Quote() {}
    public Quote(String name, Double rate, Double amount, Integer duration) {
        this.name = name;
        this.rate = rate;
        this.amount = amount;
        this.duration = duration;
    }
    
    
}
