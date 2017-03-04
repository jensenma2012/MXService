package com.xiaoma.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoma.dao.AdminDAO;
import com.xiaoma.entity.pojo.Admin;

@Repository
public class AdminDAOImpl extends BaseDAOImpl<Admin> implements AdminDAO {

    @Override
    public Admin queryByUsername(String username) throws Exception {
        String hql = "from Admin where username = :username";
        return super.getCurrentSession().createQuery(hql, Admin.class).setParameter("username", username).uniqueResult();
    }

}
