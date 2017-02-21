package com.xiaoma.entity.pojo;

public class WeChatUser extends BasePojo {

    private String username;
    private String alias;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "WeChatUser [id=" + super.getId() + ", username=" + username + ", alias=" + alias + ", createDate=" + super.getCreateDate() + "]";
    }

}
