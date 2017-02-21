package com.xiaoma.wechat.handler.impl;

import org.springframework.stereotype.Service;

import com.xiaoma.entity.enums.WeChatResponseType;
import com.xiaoma.entity.response.WeChatResponse;
import com.xiaoma.util.Constants;
import com.xiaoma.wechat.handler.WeChatHandler;

@Service
public class EchoHandler implements WeChatHandler {

    @Override
    public WeChatResponse getWelcomeMessage(String toUserName, String fromUserName) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);
        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(Constants.ECHO_SERVICE_WELCOME_MESSAGE);
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
