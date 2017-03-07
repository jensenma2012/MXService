package com.xiaoma.service.impl;

import java.util.List;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.BasePojo;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.BaseService;

public abstract class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {

    private BaseDAO<T> dao;

    public BaseDAO<T> getDAO() {
        return dao;
    }

    public void setDAO(BaseDAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public void save(T t) throws Exception {
        dao.save(t);
    }

    @Override
    public void update(T t) throws Exception {
        dao.update(t);
    }

    @Override
    public void delete(Long[] ids) throws Exception {
        dao.delete(ids);
    }

    @Override
    public List<T> queryAll() throws Exception {
        return dao.queryAll();
    }

    @Override
    public long queryCount(Pager<T> pager) throws Exception {
        return dao.queryCount(pager);
    }

    @Override
    public List<T> queryList(Pager<T> pager) throws Exception {
        return dao.queryList(pager);
    }

    @Override
    public T queryById(Long id) throws Exception {
        return dao.queryById(id);
    }

    @Override
    public Class<T> getPojoClass() {
        return dao.getPojoClass();
    }

}
