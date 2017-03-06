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
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.AlbumService;
import com.xiaoma.service.ArtistService;

@Controller
@SessionAttributes("album")
@RequestMapping("/backdoor/album/")
public class AlbumController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AlbumController.class);

    @Resource
    private AlbumService albumService;

    @Resource
    private ArtistService artistService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Album> pager) {
        LOGGER.info("accessing the album list page");
        sessionStatus.setComplete();

        try {
            long totalCount = albumService.queryCount(pager);
            List<Album> albums = albumService.queryList(pager);
            List<FieldDesc> searchFields = albumService.getSearchFields();

            pager.setTotalCount(totalCount);
            pager.setResult(albums);
            pager.setSearchFields(searchFields);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            LOGGER.error("error when fetching album list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/album/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        LOGGER.info("accessing the album add page");

        try {
            List<Artist> artists = artistService.queryAll();
            model.addAttribute("artists", artists);
        } catch (Exception e) {
            LOGGER.error("error when fetching artist list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/album/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Album album) {
        try {
            albumService.save(album);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:/backdoor/album/list";
        } catch (Exception e) {
            LOGGER.error("error when adding a new album", e);
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:/backdoor/album/add";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the album edit page");

        try {
            Album album = albumService.queryById(id);
            model.addAttribute("album", album);
        } catch (Exception e) {
            LOGGER.error("error when fetching album[id=" + id + "]", e);
            model.addAttribute("message", "获取失败！");
        }

        try {
            List<Artist> artists = artistService.queryAll();
            model.addAttribute("artists", artists);
        } catch (Exception e) {
            LOGGER.error("error when fetching artist list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/album/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Album album) {
        try {
            albumService.update(album);
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:/backdoor/album/list";
        } catch (Exception e) {
            LOGGER.error("error when updating album[id=" + album.getId() + "]", e);
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:/backdoor/album/edit?id=" + album.getId();
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> refresh() {
        HashMap<String, String> message = new HashMap<String, String>();
        albumService.refreshMusic();
        message.put("type", "success");
        message.put("content", "操作成功！");
        return message;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        HashMap<String, String> message = new HashMap<String, String>();

        try {
            albumService.delete(ids);
            message.put("type", "success");
            message.put("content", "删除成功！");
        } catch (Exception e) {
            LOGGER.error("error when deleting album[ids=" + ids + "]", e);
            message.put("type", "error");
            message.put("content", "删除失败！");
        }

        return message;
    }

}
