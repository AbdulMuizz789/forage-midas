package com.jpmc.midascore.component;

import org.springframework.stereotype.Component;

import com.jpmc.midascore.entity.*;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.*;

@Component
public class TransactionPopulator {
	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	
	public TransactionPopulator(TransactionRepository transactionRepository, UserRepository userRepository) {
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
	}
	
	public TransactionRecord create(Transaction transaction) {
		UserRecord sender = userRepository.findById(transaction.getSenderId());
		UserRecord recipient = userRepository.findById(transaction.getRecipientId());
		float amount = transaction.getAmount();
		if( sender == null || recipient == null || sender.getBalance() < amount ) return null;
		sender.setBalance(sender.getBalance()-amount);
		recipient.setBalance(amount+recipient.getBalance());
		userRepository.save(sender);
		userRepository.save(recipient);
		return new TransactionRecord(sender, recipient, amount);
	}
	
	public TransactionRecord create(Transaction transaction, float incentive) {
		TransactionRecord transactionRecord = create(transaction);
		if( transactionRecord == null) return null;
		UserRecord recipient = transactionRecord.getRecipient();
		transactionRecord.setIncentive(incentive);
		recipient.setBalance(incentive + recipient.getBalance());
		userRepository.save(recipient);
		return transactionRecord;
	}
		
	public void save(TransactionRecord transactionRecord) {
		transactionRepository.save(transactionRecord);
	}
}
