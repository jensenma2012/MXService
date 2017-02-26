package com.xiaoma.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoma.entity.pojo.Role;
import com.xiaoma.mybatis.mapper.BaseMapper;
import com.xiaoma.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Resource
    @Override
    public void setMapper(BaseMapper<Role> mapper) {
        super.setMapper(mapper);
    }

}
