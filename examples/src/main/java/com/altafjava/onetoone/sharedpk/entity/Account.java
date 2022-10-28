package com.altafjava.onetoone.sharedpk.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "employee")
public class Account implements Serializable{
	private static final long serialVersionUID = 4176073394055414884L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String accountNo;
	private String branch;
	@OneToOne(mappedBy = "account")
//	@OneToOne
	private Employee employee;
}
