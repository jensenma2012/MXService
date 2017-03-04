package com.xiaoma.entity.shared;

import java.util.ArrayList;
import java.util.List;

import com.xiaoma.entity.annotation.FieldDesc;

public class Pager<T> {

    public static final Integer MAX_PAGE_SIZE = 100;// 每页最大记录数限制

    private int pageNumber = 1;// 当前页码

    private int pageSize = 10;// 每页记录数

    private long totalCount = 0;// 总记录数

    private long pageCount = 0;// 总页数

    private int startIndex = 0;

    private List<FieldDesc> searchFields = new ArrayList<FieldDesc>();

    private String fieldName;// 查找关键字

    private String fieldValue;// 查找关键字

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

    public int getStartIndex() {
        startIndex = (pageNumber - 1) * pageSize;
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<FieldDesc> getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(List<FieldDesc> searchFields) {
        this.searchFields = searchFields;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
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

}
