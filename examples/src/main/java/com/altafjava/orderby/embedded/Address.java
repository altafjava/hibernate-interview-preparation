package com.altafjava.orderby.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Address {
	protected String street;
	protected String city;
	protected String state;
	@Embedded
	protected Zipcode zipcode;
}
