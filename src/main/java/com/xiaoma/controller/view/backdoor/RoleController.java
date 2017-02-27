package com.xiaoma.controller.view.backdoor;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.PageCondition;
import com.xiaoma.entity.pojo.Role;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.RoleService;

@Controller
@RequestMapping("/backdoor/role/")
public class RoleController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, Pager<Role> pager) {
        LOGGER.info("accessing the role list page");

        try {
            PageCondition condition = pager.getCondition();
            long totalCount = roleService.queryCount(condition);
            List<Role> roles = roleService.queryList(condition);

            pager.setTotalCount(totalCount);
            pager.setResult(roles);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            LOGGER.error("error when fetching role list", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/role/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        LOGGER.info("accessing the role add page");
        return "/backdoor/role/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Role role, String[] authorities) {
        try {
            role.setAuthorities(Arrays.asList(authorities));
            roleService.save(role);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:/backdoor/role/list";
        } catch (Exception e) {
            LOGGER.error("error when adding a new role", e);
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:/backdoor/role/add";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the role edit page");

        try {
            Role role = roleService.queryById(id);
            model.addAttribute("role", role);
        } catch (Exception e) {
            LOGGER.error("error when fetching role[id=" + id + "]", e);
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/role/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Role role, String[] authorities) {
        try {
            role.setAuthorities(Arrays.asList(authorities));
            roleService.update(role);
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:/backdoor/role/list";
        } catch (Exception e) {
            LOGGER.error("error when updating role[id=" + role.getId() + "]", e);
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:/backdoor/role/edit?id=" + role.getId();
        }
    }

}
