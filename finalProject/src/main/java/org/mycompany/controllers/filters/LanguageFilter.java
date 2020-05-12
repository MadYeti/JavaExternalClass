package org.mycompany.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/*"})
public class LanguageFilter implements Filter{

    private static final String LANG_PARAM = "lang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if(httpServletRequest.getParameter(LANG_PARAM)!=null){
            httpServletRequest.getSession().setAttribute(LANG_PARAM, httpServletRequest.getParameter(LANG_PARAM));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
