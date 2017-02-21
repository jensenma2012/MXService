package com.xiaoma.entity.pojo;

public class WeChatHistory extends BasePojo {

    private String type;
    private String content;
    private Long userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WeChatHistory [id=" + super.getId() + ", type=" + type + ", content=" + content + ", userId=" + userId + ", createDate=" + super.getCreateDate() + "]";
    }

}
