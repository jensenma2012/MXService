package com.xiaoma.controller.view.backdoor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoma.entity.pojo.WeChatHistory;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.BaseService;

@Controller
@RequestMapping("/backdoor/wechathistory")
public class WeChatHistoryController extends BaseController<WeChatHistory> {

    @Resource
    @Override
    public void setService(BaseService<WeChatHistory> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, Pager<WeChatHistory> pager) {
        return super.list(model, pager);
    }

}
