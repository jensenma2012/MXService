package com.xiaoma.controller.view.backdoor;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            if (pager == null) {
                pager = new Pager<Role>();
            }

            PageCondition condition = pager.getCondition();
            long totalCount = roleService.queryCount(condition);
            List<Role> roles = roleService.queryList(condition);

            pager.setTotalCount(totalCount);
            pager.setResult(roles);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/role/list";
    }

}
