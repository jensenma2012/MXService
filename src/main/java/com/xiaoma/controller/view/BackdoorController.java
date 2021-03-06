package com.xiaoma.controller.view;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoma.service.impl.CaptchaServiceImpl;

@Controller
@RequestMapping("/backdoor")
public class BackdoorController {

    private static final Logger LOGGER = LogManager.getLogger(BackdoorController.class);

    @Resource
    private CaptchaServiceImpl captchaService;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(String captchaId, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("creating random captcha");

        if (StringUtils.isBlank(captchaId)) {
            captchaId = request.getSession().getId();
        }

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            BufferedImage bufferedImage = captchaService.buildImage(captchaId);
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception e) {
            LOGGER.error("error when creating a captcha", e);
        } finally {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        LOGGER.info("accessing the admin main page");
        return "/backdoor/main";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        LOGGER.info("accessing the admin error page");
        return "/backdoor/error";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        LOGGER.info("accessing the admin unauthorized page");
        return "/backdoor/unauthorized";
    }

}
