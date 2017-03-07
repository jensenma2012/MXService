package com.xiaoma.controller.view.backdoor;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.Config;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.BaseService;
import com.xiaoma.service.ConfigService;

@Controller
@SessionAttributes("config")
@RequestMapping("/backdoor/config")
public class ConfigController extends BaseController<Config> {

    @Resource
    @Override
    public void setService(BaseService<Config> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Config> pager) {
        sessionStatus.setComplete();
        return super.list(model, pager);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return super.add();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Config config) {
        return super.save(redirectAttributes, config);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        return super.edit(model, id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Config config) {
        return super.update(redirectAttributes, config);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> refresh() {
        HashMap<String, String> message = new HashMap<String, String>();
        ((ConfigService) super.getService()).refreshConfigs();
        message.put("type", "success");
        message.put("content", "操作成功！");
        return message;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        return super.delete(ids);
    }

}
