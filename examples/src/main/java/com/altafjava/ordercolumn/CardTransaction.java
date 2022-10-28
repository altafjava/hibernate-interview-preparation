package com.altafjava.ordercolumn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "creditCard")
public class CardTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tid;
	private double amount;
	private String date;
	@ManyToOne
	@JoinColumn(name = "creditCardId")
	private CreditCard creditCard;
}
