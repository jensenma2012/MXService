package com.xiaoma.wechat.handler.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaoma.entity.enums.WeChatResponseType;
import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.entity.pojo.Music;
import com.xiaoma.entity.response.WeChatResponse;
import com.xiaoma.entity.shared.MusicLibrary;
import com.xiaoma.service.AlbumService;
import com.xiaoma.service.ConfigService;
import com.xiaoma.wechat.handler.WeChatHandler;

@Component
public class MusicHandler implements WeChatHandler {

    private Map<String, Object> userMap = new HashMap<String, Object>();

    @Resource
    private ConfigService configService;

    @Resource
    private AlbumService albumService;

    @Override
    public WeChatResponse getWelcomeMessage(String toUserName, String fromUserName) {
        MusicLibrary library = new MusicLibrary(albumService.getArtists());
        userMap.put(toUserName, library);

        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);
        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(configService.getValue("MUSIC_WELCOME_MESSAGE") + library.getMenu());
        return response;
    }

    @Override
    public WeChatResponse getResponse(String toUserName, String fromUserName, String content) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);

        Object object = userMap.get(toUserName);
        if (object instanceof MusicLibrary) {
            MusicLibrary library = (MusicLibrary) object;
            Artist artist = library.getArtist(content);
            if (null == artist) {
                response.setMsgType(WeChatResponseType.TEXT);
                response.setContent(library.getMenu());
            } else {
                userMap.put(toUserName, artist);
                response.setMsgType(WeChatResponseType.TEXT);
                response.setContent(artist.getMenu());
            }
        }

        if (object instanceof Artist) {
            Artist artist = (Artist) object;
            if ("上级目录".equals(content)) {
                MusicLibrary library = artist.getLibrary();
                userMap.put(toUserName, library);
                response.setMsgType(WeChatResponseType.TEXT);
                response.setContent(library.getMenu());
            } else {
                Album album = artist.getAlbum(content);
                if (null == album) {
                    response.setMsgType(WeChatResponseType.TEXT);
                    response.setContent(artist.getMenu());
                } else {
                    userMap.put(toUserName, album);
                    response.setMsgType(WeChatResponseType.TEXT);
                    response.setContent(album.getMenu());
                }
            }
        }

        if (object instanceof Album) {
            Album album = (Album) object;
            if ("上级目录".equals(content)) {
                Artist artist = album.getArtist();
                userMap.put(toUserName, artist);
                response.setMsgType(WeChatResponseType.TEXT);
                response.setContent(artist.getMenu());
            } else {
                Music music = album.getMusic(content);
                if (null == music) {
                    response.setMsgType(WeChatResponseType.TEXT);
                    response.setContent(album.getMenu());
                } else {
                    response.setMsgType(WeChatResponseType.MUSIC);
                    response.setTitle(music.getTitle());
                    response.setDescription(music.getDescription());
                    response.setMusicUrl(configService.getValue("MUSIC_URL") + music.getFilename());
                    response.sethQMusicUrl(configService.getValue("MUSIC_URL") + music.getFilename());
                }
            }
        }

        return response;
    }

}
