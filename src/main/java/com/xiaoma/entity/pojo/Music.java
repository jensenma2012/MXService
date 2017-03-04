package com.xiaoma.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoma.entity.annotation.FieldDesc;

@Entity
@Table(name = "music")
public class Music extends BasePojo {

    @FieldDesc(name = "title", desc = "歌曲标题")
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @FieldDesc(name = "description", desc = "歌曲描述")
    @Column(name = "description", nullable = false, length = 64)
    private String description;

    @Column(name = "filename", nullable = false, length = 64)
    private String filename;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Music [id=" + super.getId() + ", title=" + title + ", description=" + description + ", filename=" + filename + ", createDate=" + super.getCreateDate() + "]";
    }

}
