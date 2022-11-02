package com.altafjava.pagination.offsetlimit;

import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class PaginationService {
	public PaginationResult<Employee> paginateUsingHql(int pageNumber, int pageSize) {
		Long totalRecords = 0L;
		List<Employee> employees = Collections.emptyList();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			TypedQuery countQuery = session.createQuery("select count(emp.eid) from Employee emp");
			totalRecords = (Long) countQuery.getSingleResult();
			System.out.println("totalRecords:" + totalRecords);
		}

		int lastPage = 0;
		if (totalRecords % pageSize == 0) {
			lastPage = (int) (totalRecords / pageSize);
		} else {
			lastPage = (int) ((totalRecords / pageSize) + 1);
		}
		int offset = pageSize * (pageNumber - 1);
		System.out.println("currentPage:" + pageNumber + "  lastPage:" + lastPage + "  pageSize:" + pageSize + "  offset:" + offset);

		try (Session session = sessionFactory.openSession()) {
			TypedQuery<Employee> fromQuery = session.createQuery("from Employee");
			fromQuery.setFirstResult(offset);
			fromQuery.setMaxResults(pageSize);
			employees = fromQuery.getResultList();
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
