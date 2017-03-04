package com.xiaoma.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoma.dao.WeChatUserDAO;
import com.xiaoma.entity.pojo.WeChatUser;

@Repository
public class WeChatUserDAOImpl extends BaseDAOImpl<WeChatUser> implements WeChatUserDAO {

    @Override
    public WeChatUser queryByUsername(String username) throws Exception {
        String hql = "from WeChatUser where username = :username";
        return super.getCurrentSession().createQuery(hql, WeChatUser.class).setParameter("username", username).uniqueResult();
    }

}
