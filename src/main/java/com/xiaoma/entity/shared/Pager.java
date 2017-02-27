package com.xiaoma.entity.shared;

import java.util.ArrayList;
import java.util.List;

import com.xiaoma.entity.pojo.PageCondition;

public class Pager<T> {

    public static final Integer MAX_PAGE_SIZE = 100;// 每页最大记录数限制

    private int pageNumber = 1;// 当前页码

    private int pageSize = 10;// 每页记录数

    private long totalCount = 0;// 总记录数

    private long pageCount = 0;// 总页数

    private String keyword;// 查找关键字

    private String orderProperty;// 排序字段

    private String orderDirection;// 排序方式

    private List<T> result = new ArrayList<T>();

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageCount() {
        pageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            pageCount++;
        }
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public PageCondition getCondition() {
        return new PageCondition(keyword, (pageNumber - 1) * pageSize, pageSize, orderProperty, orderDirection);
    }

}
