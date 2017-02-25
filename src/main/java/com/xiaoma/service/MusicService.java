package com.xiaoma.service;

import java.util.List;

import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Artist;

public interface MusicService extends BaseService<Album> {

    public List<Artist> getArtists();

}
