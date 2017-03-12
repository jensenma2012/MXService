package com.xiaoma.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.WeChatHistory;
import com.xiaoma.service.WeChatHistoryService;

@Service
public class WeChatHistoryServiceImpl extends BaseServiceImpl<WeChatHistory> implements WeChatHistoryService {

    @Resource
    @Override
    public void setDAO(BaseDAO<WeChatHistory> dao) {
        super.setDAO(dao);
    }

}
