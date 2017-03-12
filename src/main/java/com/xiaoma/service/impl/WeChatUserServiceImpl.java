package com.xiaoma.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.WeChatUser;
import com.xiaoma.service.WeChatUserService;

@Service
public class WeChatUserServiceImpl extends BaseServiceImpl<WeChatUser> implements WeChatUserService {

    @Resource
    @Override
    public void setDAO(BaseDAO<WeChatUser> dao) {
        super.setDAO(dao);
    }

}
