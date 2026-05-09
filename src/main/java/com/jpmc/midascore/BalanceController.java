package com.jpmc.midascore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.foundation.Balance;

@RestController
public class BalanceController {
	@Autowired
	private DatabaseConduit dbConduit;
	
	@GetMapping("/balance")
	public Balance getBalance(@RequestParam long userId) {
		float balance = dbConduit.getBalanceById(userId);
		return new Balance(balance);
	}
}
