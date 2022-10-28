package com.altafjava.orderby.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Zipcode {
	protected String zip;
	protected int areaCode;
}
