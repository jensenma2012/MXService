package com.xiaoma.service;

import java.util.List;

import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Artist;

public interface AlbumService extends BaseService<Album> {

    public void refreshMusic();

    public List<Artist> getArtists();

}
