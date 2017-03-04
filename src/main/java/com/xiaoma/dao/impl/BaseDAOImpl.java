package com.xiaoma.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.pojo.BasePojo;
import com.xiaoma.entity.shared.Pager;

public class BaseDAOImpl<T extends BasePojo> implements BaseDAO<T> {

    @Resource
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T t) throws Exception {
        t.setCreateDate(new Date());
        getCurrentSession().save(t);
    }

    @Override
    public void update(T t) throws Exception {
        getCurrentSession().update(t);
    }

    @Override
    public void delete(Long[] ids) throws Exception {
        String hql = "delete from " + getGenericType().getName() + " where id in (:ids)";
        getCurrentSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
    }

    @Override
    public List<T> queryAll() throws Exception {
        String hql = "from " + getGenericType().getName();
        return getCurrentSession().createQuery(hql, getGenericType()).list();
    }

    @Override
    public long queryCount(Pager<T> pager) throws Exception {
        String fieldName = pager.getFieldName();
        String fieldValue = pager.getFieldValue();

        String hql = "select count(*) from " + getGenericType().getName();
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotBlank(fieldValue)) {
            hql += " where " + fieldName + " like :fieldValue";
        }

        Query<Number> query = getCurrentSession().createQuery(hql, Number.class);
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotBlank(fieldValue)) {
            query = query.setParameter("fieldValue", '%' + fieldValue + '%');
        }

        return query.uniqueResult().longValue();
    }

    @Override
    public List<T> queryList(Pager<T> pager) throws Exception {
        String fieldName = pager.getFieldName();
        String fieldValue = pager.getFieldValue();
        String orderProperty = pager.getOrderProperty();
        String orderDirection = pager.getOrderDirection();
        int startIndex = pager.getStartIndex();
        int pageSize = pager.getPageSize();

        String hql = "from " + getGenericType().getName();
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotBlank(fieldValue)) {
            hql += " where " + fieldName + " like :fieldValue";
        }
        if (StringUtils.isNotBlank(orderProperty) && StringUtils.isNotBlank(orderDirection)) {
            hql += " order by " + orderProperty + " " + orderDirection;
        }

        Query<T> query = getCurrentSession().createQuery(hql, getGenericType());
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotBlank(fieldValue)) {
            query = query.setParameter("fieldValue", '%' + fieldValue + '%');
        }

        return query.setFirstResult(startIndex).setMaxResults(pageSize).list();
    }

    @Override
    public T queryById(Long id) throws Exception {
        return getCurrentSession().get(getGenericType(), id);
    }

    @Override
    public List<FieldDesc> getSearchFields() {
        List<FieldDesc> fields = new ArrayList<FieldDesc>();
        for (Field field : getGenericType().getDeclaredFields()) {
            FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
            if (fieldDesc != null) {
                fields.add(fieldDesc);
            }
        }
        return fields;
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericType() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

}
