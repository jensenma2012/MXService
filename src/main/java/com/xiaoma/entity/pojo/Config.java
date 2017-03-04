package com.xiaoma.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoma.entity.annotation.FieldDesc;

@Entity
@Table(name = "config")
public class Config extends BasePojo {

    @FieldDesc(name = "key", desc = "配置key")
    @Column(name = "[key]", nullable = false, length = 64)
    private String key;

    @Column(name = "[value]", nullable = false, length = 255)
    private String value;

    @FieldDesc(name = "description", desc = "配置描述")
    @Column(name = "description", length = 255)
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Config [id=" + super.getId() + ", key=" + key + ", value=" + value + ", description=" + description + ", createDate=" + super.getCreateDate() + "]";
    }

}
