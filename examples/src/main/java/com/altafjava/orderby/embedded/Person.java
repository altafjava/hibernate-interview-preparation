package com.altafjava.orderby.embedded;

import java.util.Set;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OrderBy;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ElementCollection
	@OrderBy("zipcode.zip DESC")
	public Set<Address> addresses;
}
