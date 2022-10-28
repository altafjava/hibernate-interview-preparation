package com.altafjava.ordercolumn;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String name;
	private String number;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "creditCard")
	@OrderColumn(name = "transactionsOrder")
	List<CardTransaction> cardTransactions;
}
