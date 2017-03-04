package com.xiaoma.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.service.ArtistService;

@Service
public class ArtistServiceImpl extends BaseServiceImpl<Artist> implements ArtistService {

    @Resource
    @Override
    public void setDAO(BaseDAO<Artist> dao) {
        super.setDAO(dao);
    }

}
