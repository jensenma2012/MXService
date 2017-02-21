package com.xiaoma.controller.rest;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoma.entity.request.WeChatMessage;
import com.xiaoma.entity.response.WeChatResponse;
import com.xiaoma.service.WeChatService;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private static final Logger LOGGER = LogManager.getLogger(ServiceController.class);

    @Resource
    private WeChatService weChatService;

    @RequestMapping(value = "/wechat", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody String processMessage(@RequestParam(value = "echostr", required = false) String echostr, @RequestBody(required = false) WeChatMessage message) {
        if (StringUtils.isNotBlank(echostr)) {
            return echostr;
        } else if (null != message) {
            LOGGER.info("handling wechat message : " + message);
            WeChatResponse response = weChatService.createResponse(message);
            return response.toString();
        }

        return "success";
    }

}
