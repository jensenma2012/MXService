package com.xiaoma.service;

import java.util.List;

import com.xiaoma.entity.pojo.PageCondition;

public interface BaseService<T> {

    public void save(T t) throws Exception;

    public void update(T t) throws Exception;

    public void delete(Long[] ids) throws Exception;

    public List<T> queryAll() throws Exception;

    public long queryCount(PageCondition condition) throws Exception;

    public List<T> queryList(PageCondition condition) throws Exception;

    public T queryById(Long id) throws Exception;

}
