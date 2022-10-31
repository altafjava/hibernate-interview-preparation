package com.altafjava.storedprocedure.named;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
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
@NamedStoredProcedureQuery(name = Constant.NAMED_STORED_PROCEDURE_QUERY_NAME,
				procedureName = Constant.DB_PROCEDURE_NAME,
				resultClasses = { Employee.class },
				parameters = { @StoredProcedureParameter(
						name = Constant.COLUMN_NAME,
						type = Integer.class,
						mode = ParameterMode.IN) }
				)
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String firstName;
	private String lastName;
	private double salary;
}
