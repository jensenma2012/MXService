package com.xiaoma.controller.view.backdoor;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Music;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.AlbumService;
import com.xiaoma.service.ConfigService;
import com.xiaoma.service.MusicService;

@Controller
@SessionAttributes("music")
@RequestMapping("/backdoor/music/")
public class MusicController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(MusicController.class);

    @Resource
    private MusicService musicService;

    @Resource
    private AlbumService albumService;

    @Resource
    private ConfigService configService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Music> pager) {
        LOGGER.info("accessing the music list page");
        sessionStatus.setComplete();

        try {
            long totalCount = musicService.queryCount(pager);
            List<Music> musics = musicService.queryList(pager);
            List<FieldDesc> searchFields = musicService.getSearchFields();

            pager.setTotalCount(totalCount);
            pager.setResult(musics);
            pager.setSearchFields(searchFields);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            LOGGER.error("error when fetching music list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/music/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        LOGGER.info("accessing the music add page");

        try {
            List<Album> albums = albumService.queryAll();
            model.addAttribute("albums", albums);
        } catch (Exception e) {
            LOGGER.error("error when fetching album list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/music/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Music music) {
        try {
            musicService.save(music);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:/backdoor/music/list";
        } catch (Exception e) {
            LOGGER.error("error when adding a new music", e);
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:/backdoor/music/add";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the music edit page");

        try {
            Music music = musicService.queryById(id);
            model.addAttribute("music", music);
        } catch (Exception e) {
            LOGGER.error("error when fetching music[id=" + id + "]", e);
            model.addAttribute("message", "获取失败！");
        }

        try {
            List<Album> albums = albumService.queryAll();
            model.addAttribute("albums", albums);
        } catch (Exception e) {
            LOGGER.error("error when fetching album list", e);
            model.addAttribute("message", "获取失败！");
        }

        model.addAttribute("musicUrl", configService.getValue("MUSIC_URL"));
        return "/backdoor/music/edit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Music music) {
        try {
            musicService.update(music);
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:/backdoor/music/list";
        } catch (Exception e) {
            LOGGER.error("error when updating music[id=" + music.getId() + "]", e);
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:/backdoor/music/edit?id=" + music.getId();
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        HashMap<String, String> message = new HashMap<String, String>();

        try {
            musicService.delete(ids);
            message.put("type", "success");
            message.put("content", "删除成功！");
        } catch (Exception e) {
            LOGGER.error("error when deleting music[ids=" + ids + "]", e);
            message.put("type", "error");
            message.put("content", "删除失败！");
        }

        return message;
    }

}
