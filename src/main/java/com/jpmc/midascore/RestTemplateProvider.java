package com.jpmc.midascore;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.*;

public class RestTemplateProvider {
	private RestTemplate restTemplate = new RestTemplate();
	
	public ResponseEntity<Incentive> post(Transaction transaction) {
		return restTemplate.postForEntity("http://localhost:8080/incentive", transaction, Incentive.class);
	}
}
