package com.xiaoma.entity.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.xiaoma.entity.annotation.FieldDesc;

@Entity
@Table(name = "album")
public class Album extends BasePojo {

    @FieldDesc(name = "title", desc = "专辑标题")
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @FieldDesc(name = "description", desc = "专辑描述")
    @Column(name = "description", length = 255)
    private String description;

    @OneToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private List<Music> musicList;

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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public Music getMusic(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            int index = Integer.valueOf(content);
            if (index > musicList.size() || index <= 0) {
                return null;
            } else {
                return musicList.get(index - 1);
            }
        } catch (Exception e) {
            for (Music music : musicList) {
                if (content.equals(music.getTitle())) {
                    return music;
                }
            }
            return null;
        }
    }

    public String getMenu() {
        StringBuffer menu = new StringBuffer(title + "-歌曲目录\n\n");
        for (int index = 0; index < musicList.size(); index++) {
            menu.append(String.format("%02d", index + 1));
            menu.append(" - " + musicList.get(index).getTitle() + "\n");
        }
        menu.append("\n回复上级目录回到上一级，回复退出进入主目录");
        return menu.toString();
    }

    @Override
    public String toString() {
        return "Album [id=" + super.getId() + ", title=" + title + ", musicList=" + musicList + ", createDate=" + super.getCreateDate() + "]";
    }

}
