package com.xiaoma.service;

import com.xiaoma.entity.pojo.Admin;

public interface AdminService extends BaseService<Admin> {

    public Admin getCurrentUser();

    public boolean validatePassword(String password);

    public void resetPassword(String newPassword) throws Exception;

}
