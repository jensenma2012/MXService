package com.xiaoma.dao;

import com.xiaoma.entity.pojo.Admin;

public interface AdminDAO extends BaseDAO<Admin> {

    public Admin queryByUsername(String username) throws Exception;

}
