package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

	@Id
	@GeneratedValue()
	private long id;
	
	@ManyToOne
	@JoinColumn(name="senderId")
	private UserRecord sender;
	
	@ManyToOne
	@JoinColumn(name="recipientId")
	private UserRecord recipient;
	
	@Column(nullable=false)
	private float amount;
	
	protected TransactionRecord() {
	}
	
	public TransactionRecord(UserRecord sender, UserRecord recipient, float amount) {
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}

	public UserRecord getSender() {
		return sender;
	}

	public UserRecord getRecipient() {
		return recipient;
	}
	
	public Float getAmount() {
		return amount;
	}
}
