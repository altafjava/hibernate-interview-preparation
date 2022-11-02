package com.altafjava.pagination.scrollable;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;

public class PaginationService {
	public PaginationResult<Employee> paginateUsingScrollableResults(int pageNumber, int pageSize) {
		List<Employee> employees = new ArrayList<>();
		int lastPage = 0, totalRecords = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Query<Employee> fromQuery = session.createQuery("from Employee", Employee.class);
			ScrollableResults<Employee> scrollableResults = fromQuery.scroll();
			boolean hasRecords = scrollableResults.first();
			if (hasRecords) {
				int startIndex = pageSize * (pageNumber - 1);
				int endPosition = pageSize * pageNumber;
				hasRecords = scrollableResults.scroll(startIndex);
				if (hasRecords) {
					do {
						Employee employee = scrollableResults.get();
						employees.add(employee);
					} while (scrollableResults.next() && scrollableResults.getRowNumber() >= startIndex
							&& scrollableResults.getRowNumber() < endPosition);
				}
			}
		}
		PaginationResult<Employee> paginationResult = new PaginationResult<>();
		paginationResult.setCurrentPageNumber(pageNumber);
		paginationResult.setLastPageNumber(lastPage);
		paginationResult.setPageSize(pageSize);
		paginationResult.setTotalRecords(totalRecords);
		paginationResult.setRecords(employees);
		return paginationResult;
	}
}
