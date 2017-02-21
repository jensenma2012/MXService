package com.xiaoma.mybatis.mapper;

import com.xiaoma.entity.pojo.WeChatUser;

public interface WeChatUserMapper extends BaseMapper<WeChatUser> {

    public WeChatUser queryByUsername(String username) throws Exception;

}
