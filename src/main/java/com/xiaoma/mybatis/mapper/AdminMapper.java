package com.xiaoma.mybatis.mapper;

import com.xiaoma.entity.pojo.Admin;

public interface AdminMapper extends BaseMapper<Admin> {

    public Admin queryByUsername(String username) throws Exception;

}
