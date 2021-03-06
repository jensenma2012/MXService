package com.xiaoma.wechat.handler.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.xiaoma.entity.enums.WeChatResponseType;
import com.xiaoma.entity.response.WeChatResponse;
import com.xiaoma.service.ConfigService;
import com.xiaoma.util.JsonUtil;
import com.xiaoma.wechat.handler.WeChatHandler;

@Component
public class TulingHandler implements WeChatHandler {

    @Resource
    private ConfigService configService;

    @Override
    public WeChatResponse getWelcomeMessage(String toUserName, String fromUserName) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);
        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(configService.getValue("TULING_WELCOME_MESSAGE"));
        return response;
    }

    @Override
    public WeChatResponse getResponse(String toUserName, String fromUserName, String content) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);

        String result = null;
        try {
            WebTarget target = ClientBuilder.newClient().target(configService.getValue("TULING_URL"));
            target = target.queryParam("key", configService.getValue("TULING__KEY"));
            target = target.queryParam("info", content);
            Map<String, Object> resultMap = JsonUtil.convertStringToMap(target.request().get(String.class));
            result = resultMap.get("text").toString();
        } catch (Exception e) {
            result = null;
        }

        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(StringUtils.isBlank(result) ? "对不起，我没能get到你的point" : result);
        return response;
    }

}
