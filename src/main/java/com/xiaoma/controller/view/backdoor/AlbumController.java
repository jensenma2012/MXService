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

import com.xiaoma.entity.pojo.Album;
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.ArtistService;
import com.xiaoma.service.BaseService;

@Controller
@SessionAttributes("album")
@RequestMapping("/backdoor/album")
public class AlbumController extends BaseController<Album> {

    private static final Logger LOGGER = LogManager.getLogger(AlbumController.class);

    @Resource
    private ArtistService artistService;

    @Resource
    @Override
    public void setService(BaseService<Album> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Album> pager) {
        sessionStatus.setComplete();
        return super.list(model, pager);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        try {
            List<Artist> artists = artistService.queryAll();
            model.addAttribute("artists", artists);
        } catch (Exception e) {
            LOGGER.error("error when fetching artist list", e);
            model.addAttribute("message", "获取失败！");
        }

        return super.add();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Album album) {
        return super.save(redirectAttributes, album);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        try {
            List<Artist> artists = artistService.queryAll();
            model.addAttribute("artists", artists);
        } catch (Exception e) {
            LOGGER.error("error when fetching artist list", e);
            model.addAttribute("message", "获取失败！");
        }

        return super.edit(model, id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Album album) {
        return super.update(redirectAttributes, album);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        return super.delete(ids);
    }

}
