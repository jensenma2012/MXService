package com.xiaoma.entity.shared;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xiaoma.entity.pojo.Artist;

public class MusicLibrary {

    private List<Artist> artistList;

    public MusicLibrary(List<Artist> artistList) {
        super();
        for (Artist artist : artistList) {
            artist.setLibrary(this);
        }
        this.artistList = artistList;
    }

    public Artist getArtist(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            int index = Integer.valueOf(content);
            if (index > artistList.size() || index <= 0) {
                return null;
            } else {
                return artistList.get(index - 1);
            }
        } catch (Exception e) {
            for (Artist artist : artistList) {
                if (content.equals(artist.getName())) {
                    return artist;
                }
            }
            return null;
        }
    }

    public String getMenu() {
        StringBuffer menu = new StringBuffer("歌手列表\n\n");
        for (int index = 0; index < artistList.size(); index++) {
            menu.append(String.format("%02d", index + 1));
            menu.append(" - " + artistList.get(index).getName() + "\n");
        }
        menu.append("\n回复退出进入选择目录");
        return menu.toString();
    }

}
