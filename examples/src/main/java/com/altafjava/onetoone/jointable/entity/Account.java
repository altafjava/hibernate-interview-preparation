package com.altafjava.onetoone.jointable.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "employee")
public class Account implements Serializable {
	private static final long serialVersionUID = 4176073394055414884L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String accountNo;
	private String branch;
	@OneToOne(mappedBy = "account")
//	@OneToOne
//	@JoinTable(name = "employee_account", joinColumns = @JoinColumn(name = "accountId"), inverseJoinColumns = @JoinColumn(name = "employeeId"))
	private Employee employee;
}
