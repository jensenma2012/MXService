package com.xiaoma.entity.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.shared.MusicLibrary;

@Entity
@Table(name = "artist")
public class Artist extends BasePojo {

    private static final long serialVersionUID = -8854981362738481035L;

    @FieldDesc(name = "name", desc = "歌手名")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @FieldDesc(name = "alias", desc = "别名")
    @Column(name = "alias", nullable = false, length = 64)
    private String alias;

    @FieldDesc(name = "description", desc = "歌手描述")
    @Column(name = "description", length = 255)
    private String description;

    @Transient
    private MusicLibrary library;

    @Transient
    private List<Album> albumList = new ArrayList<Album>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public MusicLibrary getLibrary() {
        return library;
    }

    public void setLibrary(MusicLibrary library) {
        this.library = library;
    }

    public void addAlbum(Album album) {
        albumList.add(album);
    }

    public Album getAlbum(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            int index = Integer.valueOf(content);
            if (index > albumList.size() || index <= 0) {
                return null;
            } else {
                return albumList.get(index - 1);
            }
        } catch (Exception e) {
            for (Album album : albumList) {
                if (content.equals(album.getTitle())) {
                    return album;
                }
            }
            return null;
        }
    }

    public String getMenu() {
        StringBuffer menu = new StringBuffer(name + "-专辑目录\n\n");
        for (int index = 0; index < albumList.size(); index++) {
            menu.append(String.format("%02d", index + 1));
            menu.append(" - " + albumList.get(index).getTitle() + "\n");
        }
        menu.append("\n回复上级目录回到上一级，回复退出进入主目录");
        return menu.toString();
    }

    @Override
    public String toString() {
        return "Artist [id=" + super.getId() + ", name=" + name + ", description=" + description + ", albumList=" + albumList + ", createDate=" + super.getCreateDate() + "]";
    }

}
