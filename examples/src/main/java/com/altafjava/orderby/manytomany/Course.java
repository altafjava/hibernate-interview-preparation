package com.altafjava.orderby.manytomany;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String courseName;
	@ManyToMany(cascade = CascadeType.ALL)
	@OrderBy("name")
	private List<Student> students;
}
