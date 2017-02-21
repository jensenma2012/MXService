package com.xiaoma.entity.pojo;

public class Music extends BasePojo {

    private String title;
    private String description;
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
