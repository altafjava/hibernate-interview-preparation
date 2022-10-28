package com.altafjava.onetomany.jointable;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "employee")
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String accountNo;
	private String branch;
	@ManyToOne
	@JoinTable(name = "empacc", joinColumns = @JoinColumn(name = "accountId"), inverseJoinColumns = @JoinColumn(name = "employeeId"))
//	@JoinTable(name = "empacc", joinColumns = { @JoinColumn(name = "accountId", referencedColumnName = "aid") }, inverseJoinColumns = {
//			@JoinColumn(name = "employeeId", referencedColumnName = "eid") })
	private Employee employee;
}
