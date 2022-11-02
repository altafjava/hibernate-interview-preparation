package com.altafjava.pagination.scrollable;

import java.util.List;

public class PaginationTest {

	public static void main(String[] args) {
//		OffsetLimitTest.saveEmployees(24);
		PaginationService paginationService = new PaginationService();
		PaginationResult<Employee> paginationResult = paginationService.paginateUsingScrollableResults(2, 7);
		List<Employee> employees = paginationResult.getRecords();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
}
