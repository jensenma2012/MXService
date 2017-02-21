package com.xiaoma.controller.view;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoma.captcha.BadCaptchaException;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        LOGGER.info("accessing the main page");
        return "/index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        LOGGER.info("accessing the error page");
        return "/error";
    }

    @RequestMapping(value = "/backdoor", method = RequestMethod.GET)
    public String backdoor(ModelMap model, HttpSession session) {
        LOGGER.info("accessing the backdoor page");

        String captchaId = UUID.randomUUID().toString().replaceAll("-", "");
        model.addAttribute("captchaId", captchaId);

        Exception springSecurityLastException = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (springSecurityLastException != null) {
            String message = null;
            if (springSecurityLastException instanceof BadCredentialsException) {
                message = "您的用户名或密码错误!";
            } else if (springSecurityLastException instanceof DisabledException) {
                message = "您的账号已被禁用,无法登录!";
            } else if (springSecurityLastException instanceof BadCaptchaException) {
                message = "验证码输入错误!";
            } else {
                message = "出现未知错误,无法登录!";
            }
            model.addAttribute("errmsg", message);
            session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }

        return "/backdoor";
    }

}
