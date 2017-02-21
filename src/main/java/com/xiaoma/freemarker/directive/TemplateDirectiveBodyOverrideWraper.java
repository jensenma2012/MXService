package com.xiaoma.freemarker.directive;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class TemplateDirectiveBodyOverrideWraper implements TemplateDirectiveBody, TemplateModel {

    private TemplateDirectiveBody body;
    private TemplateDirectiveBodyOverrideWraper parentBody;
    private Environment env;

    public TemplateDirectiveBodyOverrideWraper(TemplateDirectiveBody body, Environment env) {
        super();
        this.body = body;
        this.env = env;
    }

    @Override
    public void render(Writer out) throws TemplateException, IOException {
        if (body == null)
            return;

        TemplateDirectiveBodyOverrideWraper preOverridy = (TemplateDirectiveBodyOverrideWraper) env.getVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE);
        try {
            env.setVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE, this);
            body.render(out);
        } finally {
            env.setVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE, preOverridy);
        }
    }

    public TemplateDirectiveBody getBody() {
        return body;
    }

    public void setBody(TemplateDirectiveBody body) {
        this.body = body;
    }

    public TemplateDirectiveBodyOverrideWraper getParentBody() {
        return parentBody;
    }

    public void setParentBody(TemplateDirectiveBodyOverrideWraper parentBody) {
        this.parentBody = parentBody;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

}
