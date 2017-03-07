package com.xiaoma.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoma.entity.annotation.FieldDesc;

@Entity
@Table(name = "wechat_user")
public class WeChatUser extends BasePojo {

    private static final long serialVersionUID = -2321310702638991719L;

    @FieldDesc(name = "username", desc = "微信id")
    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @FieldDesc(name = "alias", desc = "用户别名")
    @Column(name = "alias", length = 64)
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
