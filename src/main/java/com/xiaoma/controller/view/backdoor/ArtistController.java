package com.xiaoma.controller.view.backdoor;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.ArtistService;

@Controller
@SessionAttributes("artist")
@RequestMapping("/backdoor/artist/")
public class ArtistController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ArtistController.class);

    @Resource
    private ArtistService artistService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, Pager<Artist> pager) {
        LOGGER.info("accessing the artist list page");

        try {
            long totalCount = artistService.queryCount(pager);
            List<Artist> artists = artistService.queryList(pager);
            List<FieldDesc> searchFields = artistService.getSearchFields();

            pager.setTotalCount(totalCount);
            pager.setResult(artists);
            pager.setSearchFields(searchFields);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            LOGGER.error("error when fetching artist list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/artist/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        LOGGER.info("accessing the artist add page");
        return "/backdoor/artist/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Artist artist) {
        try {
            artistService.save(artist);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:/backdoor/artist/list";
        } catch (Exception e) {
            LOGGER.error("error when adding a new artist", e);
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:/backdoor/artist/add";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the artist edit page");

        try {
            Artist artist = artistService.queryById(id);
            model.addAttribute("artist", artist);
        } catch (Exception e) {
            LOGGER.error("error when fetching artist[id=" + id + "]", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/artist/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, SessionStatus sessionStatus, Artist artist) {
        try {
            artistService.update(artist);
            sessionStatus.setComplete();
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:/backdoor/artist/list";
        } catch (Exception e) {
            LOGGER.error("error when updating artist[id=" + artist.getId() + "]", e);
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:/backdoor/artist/edit?id=" + artist.getId();
        }
    }

}
