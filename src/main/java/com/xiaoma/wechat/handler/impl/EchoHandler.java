package com.xiaoma.wechat.handler.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaoma.entity.enums.WeChatResponseType;
import com.xiaoma.entity.response.WeChatResponse;
import com.xiaoma.service.ConfigService;
import com.xiaoma.wechat.handler.WeChatHandler;

@Component
public class EchoHandler implements WeChatHandler {

    @Resource
    private ConfigService configService;

    @Override
    public WeChatResponse getWelcomeMessage(String toUserName, String fromUserName) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);
        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(configService.getValue("ECHO_WELCOME_MESSAGE"));
        return response;
    }

    @Override
    public WeChatResponse getResponse(String toUserName, String fromUserName, String content) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);
        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(content);
        return response;
    }

}
