package com.jpmc.midascore.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.RestTemplateProvider;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class KafkaConsumer {
	@Autowired
	private TransactionPopulator transactionPopulator;
	
	private RestTemplateProvider provider = new RestTemplateProvider();

	@KafkaListener(id = "myId", topics = "${general.kafka-topic}")
	public void receive(ConsumerRecord<?, Transaction> data) {
		Transaction transaction = data.value();
		Incentive incentive = provider.post(transaction).getBody();
		TransactionRecord transactionRecord = transactionPopulator.create(transaction, incentive.getAmount());
		if(transactionRecord != null) transactionPopulator.save(transactionRecord);
	}
}
