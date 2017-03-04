package com.xiaoma.dao;

import com.xiaoma.entity.pojo.WeChatUser;

public interface WeChatUserDAO extends BaseDAO<WeChatUser> {

    public WeChatUser queryByUsername(String username) throws Exception;

}
