package com.xiaoma.service;

import java.util.List;

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.shared.Pager;

public interface BaseService<T> {

    public void save(T t) throws Exception;

    public void update(T t) throws Exception;

    public void delete(Long[] ids) throws Exception;

    public List<T> queryAll() throws Exception;

    public long queryCount(Pager<T> pager) throws Exception;

    public List<T> queryList(Pager<T> pager) throws Exception;

    public T queryById(Long id) throws Exception;

    public List<FieldDesc> getSearchFields();

}
