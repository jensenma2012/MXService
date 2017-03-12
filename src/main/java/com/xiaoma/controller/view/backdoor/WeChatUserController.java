package com.xiaoma.controller.view.backdoor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.WeChatUser;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.BaseService;

@Controller
@SessionAttributes("wechatuser")
@RequestMapping("/backdoor/wechatuser")
public class WeChatUserController extends BaseController<WeChatUser> {

    @Resource
    @Override
    public void setService(BaseService<WeChatUser> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<WeChatUser> pager) {
        sessionStatus.setComplete();
        return super.list(model, pager);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        return super.edit(model, id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, WeChatUser wechatuser) {
        return super.update(redirectAttributes, wechatuser);
    }

}
