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

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.pojo.Admin;
import com.xiaoma.entity.pojo.Role;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.AdminService;
import com.xiaoma.service.RoleService;

@Controller
@SessionAttributes("admin")
@RequestMapping("/backdoor/admin/")
public class AdminController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;

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
            LOGGER.error("error when resetting password for admin", e);
            message = "更新失败！";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/backdoor/admin/modify";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, SessionStatus sessionStatus, Pager<Admin> pager) {
        LOGGER.info("accessing the admin list page");
        sessionStatus.setComplete();

        try {
            long totalCount = adminService.queryCount(pager);
            List<Admin> admins = adminService.queryList(pager);
            List<FieldDesc> searchFields = adminService.getSearchFields();

            pager.setTotalCount(totalCount);
            pager.setResult(admins);
            pager.setSearchFields(searchFields);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            LOGGER.error("error when fetching admin list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/admin/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        LOGGER.info("accessing the admin add page");

        try {
            List<Role> roles = roleService.queryAll();
            model.addAttribute("roles", roles);
        } catch (Exception e) {
            LOGGER.error("error when fetching role list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/admin/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Admin admin) {
        try {
            adminService.save(admin);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:/backdoor/admin/list";
        } catch (Exception e) {
            LOGGER.error("error when adding a new admin", e);
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:/backdoor/admin/add";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the admin edit page");

        try {
            Admin admin = adminService.queryById(id);
            model.addAttribute("admin", admin);
        } catch (Exception e) {
            LOGGER.error("error when fetching admin[id=" + id + "]", e);
            model.addAttribute("message", "获取失败！");
        }

        try {
            List<Role> roles = roleService.queryAll();
            model.addAttribute("roles", roles);
        } catch (Exception e) {
            LOGGER.error("error when fetching role list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/admin/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Admin admin) {
        try {
            adminService.update(admin);
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:/backdoor/admin/list";
        } catch (Exception e) {
            LOGGER.error("error when updating admin[id=" + admin.getId() + "]", e);
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:/backdoor/admin/edit?id=" + admin.getId();
        }
    }

}
