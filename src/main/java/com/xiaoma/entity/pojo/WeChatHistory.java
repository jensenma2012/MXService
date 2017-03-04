package com.xiaoma.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xiaoma.entity.annotation.FieldDesc;

@Entity
@Table(name = "wechat_history")
public class WeChatHistory extends BasePojo {

    @FieldDesc(name = "type", desc = "消息类型")
    @Column(name = "type", nullable = false, length = 64)
    private String type;

    @FieldDesc(name = "content", desc = "消息内容")
    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @OneToOne
    @JoinColumn(name = "user_id")
    private WeChatUser wechatUser;

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

    public WeChatUser getWechatUser() {
        return wechatUser;
    }

    public void setWechatUser(WeChatUser wechatUser) {
        this.wechatUser = wechatUser;
    }

    @Override
    public String toString() {
        return "WeChatHistory [id=" + super.getId() + ", type=" + type + ", content=" + content + ", wechatUser=" + wechatUser + ", createDate=" + super.getCreateDate() + "]";
    }

}
