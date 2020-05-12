package org.mycompany.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter",
           urlPatterns = {"/*"},
           initParams = @WebInitParam(name="requestEncoding", value = "UTF-8"))
public class EncodingFilter implements Filter{

    private String encoding;
    private static final  String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if(encoding==null) {
            encoding = DEFAULT_ENCODING;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getCharacterEncoding()==null) {
            servletRequest.setCharacterEncoding(encoding);
        }
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
