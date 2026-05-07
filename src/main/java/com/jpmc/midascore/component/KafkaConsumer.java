package com.jpmc.midascore.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class KafkaConsumer {
	@Autowired
	private TransactionPopulator transactionPopulator;
	
	@KafkaListener(id = "myId", topics = "${general.kafka-topic}")
	public void receive(ConsumerRecord<?, Transaction> data) {
		System.out.println("Amount: " + data.value().getAmount());
		TransactionRecord transactionRecord = transactionPopulator.create(data.value());
		if(transactionRecord != null) transactionPopulator.save(transactionRecord);
	}
}
