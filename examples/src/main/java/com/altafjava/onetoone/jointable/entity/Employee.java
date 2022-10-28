package com.altafjava.onetoone.jointable.entity;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Employee  implements Serializable{
	private static final long serialVersionUID = 1057394680091050780L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String firstName;
	private String lastName;
	private double salary;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "employee_account", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "accountId"))
//	@JoinTable(name="employee_account")
	private Account account;
}
