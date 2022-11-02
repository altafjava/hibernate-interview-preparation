package com.altafjava.pagination.offsetlimit;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationResult<E> {
	private int currentPageNumber;
	private int lastPageNumber;
	private int pageSize;
	private long totalRecords;
	private List<E> records;
}
