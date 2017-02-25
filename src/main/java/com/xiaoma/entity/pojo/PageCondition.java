package com.xiaoma.entity.pojo;

public class PageCondition {

    private String keyword;
    private int startIndex;
    private int number;

    public PageCondition(String keyword, int startIndex, int number) {
        super();
        this.keyword = keyword;
        this.startIndex = startIndex;
        this.number = number;
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

}
