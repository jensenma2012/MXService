package com.xiaoma.controller.view.backdoor;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoma.entity.annotation.FieldDesc;
import com.xiaoma.entity.pojo.BasePojo;
import com.xiaoma.entity.shared.Pager;
import com.xiaoma.service.BaseService;

public abstract class BaseController<T extends BasePojo> {

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    private BaseService<T> service;

    public BaseService<T> getService() {
        return service;
    }

    public void setService(BaseService<T> service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ExceptionHandler
    public String handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOGGER.error("server exception", e);
        request.setAttribute("content", e.getMessage());
        return "/backdoor/error";
    }

    public String list(ModelMap model, Pager<T> pager) {
        LOGGER.info("accessing the " + getPojoName() + " list page");

        try {
            long totalCount = service.queryCount(pager);
            List<T> results = service.queryList(pager);

            pager.setTotalCount(totalCount);
            pager.setResults(results);
            pager.setSearchFields(getSearchFields());
            model.addAttribute("page", pager);
        } catch (Exception e) {
            LOGGER.error("error when fetching " + getPojoName() + " list", e);
            model.addAttribute("message", "获取失败！");
        }

        return getBasePath() + "/list";
    }

    public String add() {
        LOGGER.info("accessing the " + getPojoName() + " add page");
        return getBasePath() + "/add";
    }

    public String save(RedirectAttributes redirectAttributes, T item) {
        try {
            service.save(item);
            redirectAttributes.addFlashAttribute("message", "添加成功！");
            return "redirect:" + getBasePath() + "/list";
        } catch (Exception e) {
            LOGGER.error("error when adding a new " + getPojoName(), e);
            redirectAttributes.addFlashAttribute("message", "添加失败！");
            return "redirect:" + getBasePath() + "/add";
        }
    }

    public String edit(ModelMap model, Long id) {
        LOGGER.info("accessing the " + getPojoName() + " edit page");

        try {
            T item = service.queryById(id);
            model.addAttribute(getPojoName(), item);
        } catch (Exception e) {
            LOGGER.error("error when fetching " + getPojoName() + "[id=" + id + "]", e);
            model.addAttribute("message", "获取失败！");
        }

        return getBasePath() + "/edit";
    }

    public String update(RedirectAttributes redirectAttributes, T item) {
        try {
            service.update(item);
            redirectAttributes.addFlashAttribute("message", "修改成功！");
            return "redirect:" + getBasePath() + "/list";
        } catch (Exception e) {
            LOGGER.error("error when updating " + getPojoName() + "[id=" + item.getId() + "]", e);
            redirectAttributes.addFlashAttribute("message", "修改失败！");
            return "redirect:" + getBasePath() + "/edit?id=" + item.getId();
        }
    }

    public @ResponseBody HashMap<String, String> delete(Long[] ids) {
        HashMap<String, String> message = new HashMap<String, String>();

        try {
            service.delete(ids);
            message.put("type", "success");
            message.put("content", "删除成功！");
        } catch (Exception e) {
            LOGGER.error("error when deleting " + getPojoName() + "[ids=" + ids + "]", e);
            message.put("type", "error");
            message.put("content", "删除失败！");
        }

        return message;
    }

    private String getBasePath() {
        return getClass().getAnnotation(RequestMapping.class).value()[0];
    }

    private String getPojoName() {
        return service.getPojoClass().getSimpleName().toLowerCase();
    }

    private List<FieldDesc> getSearchFields() {
        List<FieldDesc> fields = new ArrayList<FieldDesc>();
        for (Field field : service.getPojoClass().getDeclaredFields()) {
            FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
            if (fieldDesc != null) {
                fields.add(fieldDesc);
            }
        }
        return fields;
    }

}
