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

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.service.AlbumService;

@Service
public class AlbumServiceImpl extends BaseServiceImpl<Album> implements AlbumService {

    private static final Logger LOGGER = LogManager.getLogger(AlbumServiceImpl.class);

    private List<Artist> artists;

    @Resource
    @Override
    public void setDAO(BaseDAO<Album> dao) {
        super.setDAO(dao);
    }

    @PostConstruct
    public void init() {
        LOGGER.info("start loading music");

        artists = new ArrayList<Artist>();
        loadMusic();

        LOGGER.info("done loading music");
    }

    private void loadMusic() {
        try {
            List<Album> albums = queryAll();
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
            LOGGER.error("error when loading music", e);
        }
    }

    @Override
    public List<Artist> getArtists() {
        return artists;
    }

}
