package com.xiaoma.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xiaoma.entity.enums.WeChatResponseType;
import com.xiaoma.entity.pojo.WeChatHistory;
import com.xiaoma.entity.pojo.WeChatUser;
import com.xiaoma.entity.request.WeChatMessage;
import com.xiaoma.entity.response.WeChatResponse;
import com.xiaoma.mybatis.mapper.WeChatHistoryMapper;
import com.xiaoma.mybatis.mapper.WeChatUserMapper;
import com.xiaoma.service.WeChatService;
import com.xiaoma.util.Constants;
import com.xiaoma.wechat.handler.WeChatHandler;
import com.xiaoma.wechat.handler.impl.EchoHandler;
import com.xiaoma.wechat.handler.impl.MusicHandler;
import com.xiaoma.wechat.handler.impl.ProphetHandler;
import com.xiaoma.wechat.handler.impl.TulingHandler;

@Service
public class WeChatServiceImpl implements WeChatService {

    private static final Logger LOGGER = LogManager.getLogger(WeChatService.class);

    private Map<String, WeChatHandler> handlerMap = new HashMap<String, WeChatHandler>();

    @Resource
    private WeChatUserMapper wechatUserMapper;

    @Resource
    private WeChatHistoryMapper wechatHistoryMapper;

    @Resource
    private EchoHandler echoHandler;

    @Resource
    private TulingHandler tulingHandler;

    @Resource
    private ProphetHandler prophetHandler;

    @Resource
    private MusicHandler musicHandler;

    @Override
    public WeChatResponse createResponse(WeChatMessage message) {
        WeChatResponse response = new WeChatResponse();
        String username = message.getFromUserName();

        switch (message.getMsgType()) {
        case "event":
            LOGGER.info("handling event message");
            response = createResponse(username, message.getToUserName(), message.getContent());

            if ("subscribe".equals(message.getEvent())) {
                LOGGER.info("user[" + username + "] just subscribed");
                response.setContent("你好，多谢关注\n" + response.getContent());

                try {
                    WeChatUser wechatUser = new WeChatUser();
                    wechatUser.setUsername(username);
                    wechatUserMapper.save(wechatUser);
                } catch (Exception e) {
                    LOGGER.error("error when saving user[" + username + "] to wechat_user", e);
                }
            }

            if ("unsubscribe".equals(message.getEvent())) {
                LOGGER.info("user[" + username + "] just unsubscribed");
                response.setContent("再见");

                try {
                    WeChatUser wechatUser = wechatUserMapper.queryByUsername(username);
                    if (wechatUser != null) {
                        wechatUserMapper.delete(new Long[] { wechatUser.getId() });
                    }
                } catch (Exception e) {
                    LOGGER.error("error when removing user[" + username + "] from wechat_user", e);
                }
            }

            break;
        case "text":
            String content = message.getContent();
            LOGGER.info("handling text message with content[" + content + "]");
            response = createResponse(username, message.getToUserName(), content);

            try {
                WeChatUser wechatUser = wechatUserMapper.queryByUsername(username);
                if (wechatUser == null) {
                    wechatUser = new WeChatUser();
                    wechatUser.setUsername(username);
                    wechatUserMapper.save(wechatUser);
                }

                WeChatHistory wechatHistory = new WeChatHistory();
                wechatHistory.setType(message.getMsgType());
                wechatHistory.setContent(content);
                wechatHistory.setUserId(wechatUser.getId());
                wechatHistoryMapper.save(wechatHistory);
            } catch (Exception e) {
                LOGGER.error("error when saving message[" + content + "] to wechat_history", e);
            }

            break;
        default:
            LOGGER.info("handling other message with type[" + message.getMsgType() + "]");
            response.setToUserName(username);
            response.setFromUserName(message.getToUserName());
            response.setMsgType(WeChatResponseType.TEXT);
            response.setContent("暂时只支持文字消息，抱歉~");
            break;
        }

        return response;
    }

    private WeChatResponse createResponse(String toUserName, String fromUserName, String content) {
        WeChatResponse response = new WeChatResponse();
        WeChatHandler handler = handlerMap.get(toUserName);

        if ("退出".equals(content) || null == content) {
            handler = null;
            response = getWelcomeMessage(toUserName, fromUserName);
        } else {
            if (null == handler) {
                switch (content) {
                case "1":
                    handler = echoHandler;
                    response = handler.getWelcomeMessage(toUserName, fromUserName);
                    break;
                case "2":
                    handler = tulingHandler;
                    response = handler.getWelcomeMessage(toUserName, fromUserName);
                    break;
                case "3":
                    handler = prophetHandler;
                    response = handler.getWelcomeMessage(toUserName, fromUserName);
                    break;
                case "4":
                    handler = musicHandler;
                    response = handler.getWelcomeMessage(toUserName, fromUserName);
                    break;
                default:
                    handler = null;
                    response = getWelcomeMessage(toUserName, fromUserName);
                    break;
                }
            } else {
                response = handler.getResponse(toUserName, fromUserName, content);
            }
        }

        handlerMap.put(toUserName, handler);
        return response;
    }

    private WeChatResponse getWelcomeMessage(String toUserName, String fromUserName) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUserName);
        response.setFromUserName(fromUserName);
        response.setMsgType(WeChatResponseType.TEXT);
        response.setContent(Constants.WELCOME_MESSAGE);
        return response;
    }

}
