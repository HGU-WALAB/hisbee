package com.hcu.hot6.domain.filter;

import com.hcu.hot6.domain.Department;
import com.hcu.hot6.domain.enums.OrderBy;
import com.hcu.hot6.domain.enums.Year;
import com.hcu.hot6.util.Utils;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class PostSearchFilter {

    @Builder
    public PostSearchFilter(Integer page,
                            String type,
                            String keywords,
                            OrderBy orderBy,
                            Integer limit,
                            Department department,
                            Boolean myDeptOnly,
                            Year year,
                            Boolean isClosed) {
        this.page = page;
        this.type = type;
        this.keywords = Utils.toArray(keywords, ",");
        this.orderBy = Objects.requireNonNullElse(orderBy, OrderBy.RECENT);
        this.limit = limit;
        this.department = department;
        this.myDeptOnly = myDeptOnly;
        this.year = year;
        this.isClosed = isClosed;
    }

    private final Integer page;
    private final String type;
    private final List<String> keywords;
    private final OrderBy orderBy;
    private final Integer limit;
    private final Department department;
    private final Boolean myDeptOnly;
    private final Year year;
    private final Boolean isClosed;
}
