package com.jpmc.midascore.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.foundation.Transaction;

@Component
public class KafkaConsumer {
	@KafkaListener(id = "myId", topics = "${general.kafka-topic}")
	public void receive(ConsumerRecord<?, Transaction> data) {
		System.out.println("Amount: " + data.value().getAmount());
	}
}
