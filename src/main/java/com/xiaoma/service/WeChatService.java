package com.xiaoma.service;

import com.xiaoma.entity.request.WeChatMessage;
import com.xiaoma.entity.response.WeChatResponse;

public interface WeChatService {

    public WeChatResponse createResponse(WeChatMessage message);

}
