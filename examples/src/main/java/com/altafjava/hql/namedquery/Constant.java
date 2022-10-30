package com.altafjava.hql.namedquery;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {
	public static final String GET_EMPLOYEE_BY_ID_NAME = "GET_EMPLOYEE_BY_ID";
	public static final String GET_EMPLOYEE_BY_ID_QUERY = "from Employee where eid=:id";
}
