package com.xiaoma.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ExtendsDirective implements TemplateDirectiveModel {

    public final static String DIRECTIVE_NAME = "extends";

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String name = DirectiveUtils.getRequiredParam(params, "name");
        String encoding = DirectiveUtils.getParam(params, "encoding", null);
        String includeTemplateName = env.toFullTemplateName(getTemplatePath(env), name);
        env.include(includeTemplateName, encoding, true);
    }

    private String getTemplatePath(Environment env) {
        String templateName = ((Template) env.getParent()).getName();
        return templateName.lastIndexOf('/') == -1 ? "" : templateName.substring(0, templateName.lastIndexOf('/') + 1);
    }

}