package com.xiaoma.service.impl;

import java.util.List;

import com.xiaoma.entity.pojo.PageCondition;
import com.xiaoma.mybatis.mapper.BaseMapper;
import com.xiaoma.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseMapper<T> mapper;

    public BaseMapper<T> getMapper() {
        return mapper;
    }

    public void setMapper(BaseMapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(T t) throws Exception {
        mapper.save(t);
    }

    @Override
    public void update(T t) throws Exception {
        mapper.update(t);
    }

    @Override
    public void delete(Long[] ids) throws Exception {
        mapper.delete(ids);
    }

    @Override
    public List<T> queryAll() throws Exception {
        return mapper.queryAll();
    }

    @Override
    public long queryCount(PageCondition condition) throws Exception {
        return mapper.queryCount(condition);
    }

    @Override
    public List<T> queryList(PageCondition condition) throws Exception {
        return mapper.queryList(condition);
    }

    @Override
    public T queryById(Long id) throws Exception {
        return mapper.queryById(id);
    }

}
