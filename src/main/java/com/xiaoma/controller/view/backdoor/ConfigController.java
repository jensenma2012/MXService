package com.xiaoma.controller.view.backdoor;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.pojo.Config;
import com.xiaoma.entity.pojo.PageCondition;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.mybatis.mapper.ConfigMapper;

@Controller
@RequestMapping("/backdoor/config/")
public class ConfigController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ConfigController.class);

    @Resource
    private ConfigMapper configMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, Pager<Config> pager) {
        LOGGER.info("accessing the config list page");

        try {
            if (pager == null) {
                pager = new Pager<Config>();
            }

            PageCondition condition = pager.getCondition();
            long totalCount = configMapper.queryCount(condition);
            List<Config> configs = configMapper.queryList(condition);

            pager.setTotalCount(totalCount);
            pager.setResult(configs);
            model.addAttribute("page", pager);
        } catch (Exception e) {
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/config/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        LOGGER.info("accessing the config add page");
        return "/backdoor/config/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RedirectAttributes redirectAttributes, Config config) {
        try {
            configMapper.save(config);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:/backdoor/config/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:/backdoor/config/add";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the config edit page");

        try {
            Config config = configMapper.queryById(id);
            model.addAttribute("config", config);
        } catch (Exception e) {
            model.addAttribute("message", "获取失败！");
        }

        return "/backdoor/config/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Config config) {
        try {
            configMapper.update(config);
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:/backdoor/config/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:/backdoor/config/edit";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        HashMap<String, String> message = new HashMap<String, String>();

        try {
            configMapper.delete(ids);
            message.put("type", "success");
            message.put("content", "删除成功！");
        } catch (Exception e) {
            message.put("type", "error");
            message.put("content", "删除失败！");
        }

        return message;
    }

}
