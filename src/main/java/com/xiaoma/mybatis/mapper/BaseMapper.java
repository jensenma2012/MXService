package com.xiaoma.mybatis.mapper;

import java.util.List;

public interface BaseMapper<T> {

    public void save(T t) throws Exception;

    public void update(T t) throws Exception;

    public void delete(Long[] ids) throws Exception;

    public List<T> queryAll() throws Exception;

    public T queryById(Long id) throws Exception;

}
