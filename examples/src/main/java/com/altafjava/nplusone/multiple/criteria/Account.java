package com.altafjava.nplusone.multiple.criteria;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
@ToString(exclude = "employee")
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String accountNo;
	private String branch;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employeeId")
	private Employee employee;
}
