package com.xiaoma.controller.view.backdoor;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.Admin;
import com.xiaoma.entity.pojo.Role;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.AdminService;
import com.xiaoma.service.BaseService;
import com.xiaoma.service.RoleService;

@Controller
@SessionAttributes("admin")
@RequestMapping("/backdoor/admin")
public class AdminController extends BaseController<Admin> {

    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    @Resource
    private RoleService roleService;

    @Resource
    @Override
    public void setService(BaseService<Admin> service) {
        super.setService(service);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(ModelMap model) {
        LOGGER.info("accessing the admin modify page");
        Admin currentUser = ((AdminService) super.getService()).getCurrentUser();
        model.addAttribute("username", currentUser.getUsername());
        return "/backdoor/admin/modify";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(ModelMap model, RedirectAttributes redirectAttributes, String password, String newPassword) {
        boolean isPasswordValid = ((AdminService) super.getService()).validatePassword(password);
        String message = "更新成功！";
        try {
            if (isPasswordValid) {
                ((AdminService) super.getService()).resetPassword(newPassword);
            } else {
                message = "密码输入错误！";
            }
        } catch (Exception e) {
            LOGGER.error("error when resetting password for admin", e);
            message = "更新失败！";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/backdoor/admin/modify";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Admin> pager) {
        sessionStatus.setComplete();
        return super.list(model, pager);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        try {
            List<Role> roles = roleService.queryAll();
            model.addAttribute("roles", roles);
        } catch (Exception e) {
            LOGGER.error("error when fetching role list", e);
            model.addAttribute("message", "获取失败！");
        }

        return super.add();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Admin admin) {
        return super.save(redirectAttributes, admin);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        try {
            List<Role> roles = roleService.queryAll();
            model.addAttribute("roles", roles);
        } catch (Exception e) {
            LOGGER.error("error when fetching role list", e);
            model.addAttribute("message", "获取失败！");
        }

        return super.edit(model, id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Admin admin) {
        return super.update(redirectAttributes, admin);
    }

}
