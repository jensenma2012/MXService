package com.xiaoma.wechat.handler;

import com.xiaoma.entity.response.WeChatResponse;

public interface WeChatHandler {

    public WeChatResponse getWelcomeMessage(String toUserName, String fromUserName);

    public WeChatResponse getResponse(String toUserName, String fromUserName, String content);

}
