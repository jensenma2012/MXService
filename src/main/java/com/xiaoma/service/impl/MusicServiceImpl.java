package com.xiaoma.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.Music;
import com.xiaoma.service.MusicService;

@Service
public class MusicServiceImpl extends BaseServiceImpl<Music> implements MusicService {

    @Resource
    @Override
    public void setDAO(BaseDAO<Music> dao) {
        super.setDAO(dao);
    }

}
