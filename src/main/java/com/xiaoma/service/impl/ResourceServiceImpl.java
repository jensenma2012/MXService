package com.xiaoma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.entity.pojo.Config;
import com.xiaoma.mybatis.mapper.AlbumMapper;
import com.xiaoma.mybatis.mapper.ConfigMapper;
import com.xiaoma.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static final Logger LOGGER = LogManager.getLogger(ResourceService.class);

    private Map<String, String> configMap;
    private List<Artist> artists;

    @Resource
    private ConfigMapper configMapper;

    @Resource
    private AlbumMapper albumMapper;

    @PostConstruct
    public void init() {
        LOGGER.info("start loading resources");

        configMap = new HashMap<String, String>();
        artists = new ArrayList<Artist>();
        loadResource();

        LOGGER.info("done loading resources");
    }

    @Override
    public void refreshResource() {
        LOGGER.info("start refreshing resources");

        configMap.clear();
        artists.clear();
        loadResource();

        LOGGER.info("done refreshing resources");
    }

    private void loadResource() {
        LOGGER.info("start loading properties and music");

        try {
            for (Config config : configMapper.queryAll()) {
                configMap.put(config.getKey(), config.getValue());
            }
            LOGGER.info("properties : " + configMap);

            List<Album> albums = albumMapper.queryAll();
            Map<Long, Artist> artistMap = new HashMap<Long, Artist>();
            for (Album album : albums) {
                Artist artist = album.getArtist();
                Long artist_id = artist.getId();
                artist = (null == artistMap.get(artist_id)) ? artist : artistMap.get(artist_id);
                artist.addAlbum(album);
                artistMap.put(artist_id, artist);
            }

            for (Album album : albums) {
                Artist artist = album.getArtist();
                Long artist_id = artist.getId();
                album.setArtist(artistMap.get(artist_id));
            }

            for (Artist artist : artistMap.values()) {
                artists.add(artist);
            }
            LOGGER.info("music : " + artists);
        } catch (Exception e) {
            LOGGER.error("error when loading properties and music", e);
        }

        LOGGER.info("done loading properties and music");
    }

    @Override
    public String getValue(String key) {
        return configMap.get(key);
    }

    @Override
    public List<Artist> getArtists() {
        return artists;
    }

}
