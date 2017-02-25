package com.xiaoma.service;

import com.xiaoma.entity.pojo.Config;

public interface ConfigService extends BaseService<Config> {

    public void refreshConfigs();

    public String getValue(String key);

}
