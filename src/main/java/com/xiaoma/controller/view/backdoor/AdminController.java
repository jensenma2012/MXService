package com.xiaoma.controller.view.backdoor;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.Admin;
import com.xiaoma.service.AdminService;

@Controller
@RequestMapping("/backdoor/admin/")
public class AdminController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(ModelMap model) {
        LOGGER.info("accessing the admin modify page");
        Admin currentUser = adminService.getCurrentUser();
        model.addAttribute("username", currentUser.getUsername());
        return "/backdoor/admin/modify";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(ModelMap model, RedirectAttributes redirectAttributes, String password, String newPassword) {
        boolean isPasswordValid = adminService.validatePassword(password);
        String message = "更新成功！";
        try {
            if (isPasswordValid) {
                adminService.resetPassword(newPassword);
            } else {
                message = "密码输入错误！";
            }
        } catch (Exception e) {
            message = "更新失败！";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/backdoor/admin/modify";
    }

}
