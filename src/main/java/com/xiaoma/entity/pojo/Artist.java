package com.xiaoma.entity.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xiaoma.entity.shared.MusicLibrary;

public class Artist extends BasePojo {

    private String name;
    private MusicLibrary library;
    private List<Album> albumList = new ArrayList<Album>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        menu.append("\n回复上级目录回到上一级，回复退出进入选择目录");
        return menu.toString();
    }

    @Override
    public String toString() {
        return "Artist [id=" + super.getId() + ", name=" + name + ", albumList=" + albumList + ", createDate=" + super.getCreateDate() + "]";
    }

}
