package com.xiaoma.service;

import java.util.List;

import com.xiaoma.entity.pojo.Artist;

public interface ResourceService {

    public void refreshResource();

    public String getValue(String key);

    public List<Artist> getArtists();

}
