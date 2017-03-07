package com.xiaoma.controller.view.backdoor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.BaseService;

@Controller
@SessionAttributes("artist")
@RequestMapping("/backdoor/artist")
public class ArtistController extends BaseController<Artist> {

    @Resource
    @Override
    public void setService(BaseService<Artist> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Artist> pager) {
        sessionStatus.setComplete();
        return super.list(model, pager);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return super.add();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Artist artist) {
        return super.save(redirectAttributes, artist);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        return super.edit(model, id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Artist artist) {
        return super.update(redirectAttributes, artist);
    }

}
