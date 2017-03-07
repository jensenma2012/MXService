package com.xiaoma.controller.view.backdoor;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Music;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.AlbumService;
import com.xiaoma.service.BaseService;
import com.xiaoma.service.ConfigService;

@Controller
@SessionAttributes("music")
@RequestMapping("/backdoor/music")
public class MusicController extends BaseController<Music> {

    private static final Logger LOGGER = LogManager.getLogger(MusicController.class);

    @Resource
    private AlbumService albumService;

    @Resource
    private ConfigService configService;

    @Resource
    @Override
    public void setService(BaseService<Music> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Music> pager) {
        sessionStatus.setComplete();
        return super.list(model, pager);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        try {
            List<Album> albums = albumService.queryAll();
            model.addAttribute("albums", albums);
        } catch (Exception e) {
            LOGGER.error("error when fetching album list", e);
            model.addAttribute("message", "获取失败！");
        }

        return super.add();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, @RequestParam("file") CommonsMultipartFile file, Music music) {
        try {
            Album album = albumService.queryById(music.getAlbum().getId());
            String filename = album.getArtist().getAlias() + "-" + album.getId() + "-" + System.currentTimeMillis() + ".mp3";
            String filepath = configService.getValue("MUSIC_FOLDER") + filename;
            File musicFile = new File(filepath);
            file.transferTo(musicFile);
            music.setFilename(filename);
        } catch (Exception e) {
            LOGGER.error("error when fetching album[id=" + music.getAlbum().getId() + "]", e);
        }

        return super.save(redirectAttributes, music);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        try {
            List<Album> albums = albumService.queryAll();
            model.addAttribute("albums", albums);
        } catch (Exception e) {
            LOGGER.error("error when fetching album list", e);
            model.addAttribute("message", "获取失败！");
        }

        model.addAttribute("musicUrl", configService.getValue("MUSIC_URL"));
        return super.edit(model, id);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Music music) {
        return super.update(redirectAttributes, music);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> refresh() {
        HashMap<String, String> message = new HashMap<String, String>();
        albumService.refreshMusic();
        message.put("type", "success");
        message.put("content", "操作成功！");
        return message;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        return super.delete(ids);
    }

}
