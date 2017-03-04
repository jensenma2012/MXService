package com.xiaoma.entity.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.util.JsonUtil;

@Entity
@Table(name = "role")
public class Role extends BasePojo {

    @FieldDesc(name = "name", desc = "权限名")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "authority_list", nullable = false, length = 255)
    private String authorityList;

    @FieldDesc(name = "description", desc = "权限描述")
    @Column(name = "description", length = 255)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(String authorityList) {
        this.authorityList = authorityList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthorities() {
        if (StringUtils.isBlank(authorityList)) {
            return null;
        } else {
            if (!authorityList.contains("[")) {
                String[] authorities = authorityList.split(",");
                StringBuilder builder = new StringBuilder("[");
                int count = authorities.length;
                int size = 1;
                for (String str : authorities) {
                    builder.append("\"" + str.trim() + "\"");
                    if (size < count) {
                        builder.append(",");
                    }
                    size++;
                }
                builder.append("]");
                authorityList = builder.toString();
            }
            return JsonUtil.convertStringToList(authorityList);
        }
    }

    public void setAuthorities(List<String> authorities) {
        authorityList = JsonUtil.convertListToString(authorities);
    }

    @Override
    public String toString() {
        return "Role [id=" + super.getId() + ", name=" + name + ", authorityList=" + authorityList + ", description=" + description + ", createDate=" + super.getCreateDate() + "]";
    }

}
