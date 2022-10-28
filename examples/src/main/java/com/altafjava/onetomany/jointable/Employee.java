package com.altafjava.onetomany.jointable;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Employee implements Serializable {
	private static final long serialVersionUID = -6165525351020151113L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String firstName;
	private String lastName;
	private double salary;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "empacc", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "accountId"))
//	@JoinTable(name = "empacc", joinColumns = { @JoinColumn(name = "employeeId", referencedColumnName = "eid") }, inverseJoinColumns = {
//			@JoinColumn(name = "accountId", referencedColumnName = "aid") })
	private List<Account> accounts;
}
