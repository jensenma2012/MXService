package com.xiaoma.service.impl;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.octo.captcha.service.CaptchaService;

public class CaptchaServiceImpl {

    @Resource
    private CaptchaService imageCaptchaService;

    public BufferedImage buildImage(String captchaId) {
        return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
    }

    public boolean isValid(String captchaId, String captcha) {
        boolean flag = false;
        if (StringUtils.isNotBlank(captchaId) && StringUtils.isNotBlank(captcha)) {
            try {
                flag = imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
            } catch (Exception e) {
                return false;
            }
        }

        return flag;
    }

}