package com.xiaoma.captcha;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xiaoma.service.impl.CaptchaServiceImpl;

public class CaptchaFilter implements Filter {

    public static final String PARAM_FAILURE_URL = "failureUrl";
    public static final String PARAM_CAPTCHA_SERVICE = "captchaService";

    public static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    public static final String DEFAULT_CAPTCHA_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    private String failureUrl;
    private String captchaService;

    private CaptchaServiceImpl captchaServiceimpl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initParameters(filterConfig);
        initCaptchaService(filterConfig);
    }

    private void initParameters(final FilterConfig fConfig) {
        if (StringUtils.isBlank(fConfig.getInitParameter(PARAM_FAILURE_URL))) {
            throw new IllegalArgumentException("CaptchaFilter缺少failureUrl参数");
        }
        failureUrl = fConfig.getInitParameter(PARAM_FAILURE_URL);

        if (StringUtils.isBlank(fConfig.getInitParameter(PARAM_CAPTCHA_SERVICE))) {
            throw new IllegalArgumentException("CaptchaFilter缺少captchaService参数");
        }
        captchaService = fConfig.getInitParameter(PARAM_CAPTCHA_SERVICE);
    }

    private void initCaptchaService(final FilterConfig fConfig) {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(fConfig.getServletContext());
        captchaServiceimpl = (CaptchaServiceImpl) context.getBean(captchaService);
    }

    @Override
    public void doFilter(ServletRequest theRequest, ServletResponse theResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) theRequest;
        HttpServletResponse response = (HttpServletResponse) theResponse;

        boolean validated = validateCaptchaChallenge(request);
        if (validated) {
            chain.doFilter(request, response);
        } else {
            redirectFailureUrl(request, response);
        }
    }

    private boolean validateCaptchaChallenge(final HttpServletRequest request) {
        String captchaId = request.getParameter(DEFAULT_CAPTCHA_ID_PARAM);
        if (StringUtils.isBlank(captchaId)) {
            captchaId = request.getSession().getId();
        }
        String captcha = request.getParameter(DEFAULT_CAPTCHA_PARAM);
        return captchaServiceimpl.isValid(captchaId, captcha);
    }

    private void redirectFailureUrl(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(DEFAULT_CAPTCHA_EXCEPTION, new BadCaptchaException());
        response.sendRedirect(request.getContextPath() + failureUrl);
    }

    @Override
    public void destroy() {

    }

}
