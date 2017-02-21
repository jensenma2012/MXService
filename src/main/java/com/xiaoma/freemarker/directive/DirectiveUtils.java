package com.xiaoma.freemarker.directive;

import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

public class DirectiveUtils {

    public static String BLOCK = "__ftl_override__";
    public static String OVERRIDE_CURRENT_NODE = "__ftl_override_current_node";

    public static String getOverrideVariableName(String name) {
        return BLOCK + name;
    }

    public static void exposeRapidMacros(Configuration conf) {
        conf.setSharedVariable(BlockDirective.DIRECTIVE_NAME, new BlockDirective());
        conf.setSharedVariable(ExtendsDirective.DIRECTIVE_NAME, new ExtendsDirective());
        conf.setSharedVariable(OverrideDirective.DIRECTIVE_NAME, new OverrideDirective());
    }

    @SuppressWarnings("rawtypes")
    public static String getRequiredParam(Map params, String key) throws TemplateException {
        Object value = params.get(key);
        if (value == null) {
            throw new TemplateModelException("not found required parameter:" + key + " for directive");
        }
        return value.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String getParam(Map params, String key, String defaultValue) throws TemplateException {
        Object value = params.get(key);
        return value == null ? defaultValue : value.toString();
    }

    public static TemplateDirectiveBodyOverrideWraper getOverrideBody(Environment env, String name) throws TemplateModelException {
        TemplateDirectiveBodyOverrideWraper value = (TemplateDirectiveBodyOverrideWraper) env.getVariable(DirectiveUtils.getOverrideVariableName(name));
        return value;
    }

    public static void setTopBodyForParentBody(Environment env, TemplateDirectiveBodyOverrideWraper topBody, TemplateDirectiveBodyOverrideWraper overrideBody) {
        TemplateDirectiveBodyOverrideWraper parent = overrideBody;
        while (parent.getParentBody() != null) {
            parent = parent.getParentBody();
        }
        parent.setParentBody(topBody);
    }

}
