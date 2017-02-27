package com.xiaoma.entity.pojo;

public class PageCondition {

    private String keyword;
    private int startIndex;
    private int number;
    private String orderProperty;
    private String orderDirection;

    public PageCondition(String keyword, int startIndex, int number, String orderProperty, String orderDirection) {
        super();
        this.keyword = keyword;
        this.startIndex = startIndex;
        this.number = number;
        this.orderProperty = orderProperty;
        this.orderDirection = orderDirection;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
