package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Incentive {
	private float amount;
	
	public Incentive() {
	}
	
	public Incentive(float incentive) {
		this.amount = incentive;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float incentive) {
		this.amount = incentive;
	}
	
	@Override
    public String toString() {
        return "Incentive {amount=" + amount + "}";
    }
}
