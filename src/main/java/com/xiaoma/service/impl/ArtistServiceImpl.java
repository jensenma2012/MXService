package com.xiaoma.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoma.entity.pojo.Artist;
import com.xiaoma.mybatis.mapper.BaseMapper;
import com.xiaoma.service.ArtistService;

@Service
public class ArtistServiceImpl extends BaseServiceImpl<Artist> implements ArtistService {

    @Resource
    @Override
    public void setMapper(BaseMapper<Artist> mapper) {
        super.setMapper(mapper);
    }

}
