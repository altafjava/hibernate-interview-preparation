package com.altafjava.batch.multipleentities;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Employee implements Serializable {
	@Id
	private int eid;
	private String firstName;
	private String lastName;
	private double salary;
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "employee")
	private List<Account> accounts;
}
