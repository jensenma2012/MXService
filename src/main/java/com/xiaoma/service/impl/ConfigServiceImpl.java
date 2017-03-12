package com.xiaoma.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xiaoma.dao.BaseDAO;
import com.xiaoma.entity.pojo.Config;
import com.xiaoma.service.ConfigService;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {

    private static final Logger LOGGER = LogManager.getLogger(ConfigServiceImpl.class);

    private Map<String, String> configMap;

    @Resource
    @Override
    public void setDAO(BaseDAO<Config> dao) {
        super.setDAO(dao);
    }

    @PostConstruct
    public void init() {
        LOGGER.info("start loading configs");

        configMap = new HashMap<String, String>();
        loadConfigs();

        LOGGER.info("done loading configs");
    }

    @Override
    public void refreshConfigs() {
        LOGGER.info("start refreshing configs");

        configMap.clear();
        loadConfigs();

        LOGGER.info("done refreshing configs");
    }

    private void loadConfigs() {
        try {
            for (Config config : queryAll()) {
                configMap.put(config.getKey(), config.getValue().replace("\r\n", "\n"));
            }
            LOGGER.info("configs : " + configMap);
        } catch (Exception e) {
            LOGGER.error("error when loading configs", e);
        }
    }

    @Override
    public String getValue(String key) {
        return configMap.get(key);
    }

}
