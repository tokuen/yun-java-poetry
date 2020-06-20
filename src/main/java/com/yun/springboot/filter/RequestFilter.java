package com.yun.springboot.filter;

import com.yun.springboot.util.MyUtil;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = {"/*"})
@Order(value = 1)
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException,ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String request_id = req.getParameter("request_id");
        if(MyUtil.isEmpty4Object(request_id)){
            request_id = UUID.randomUUID().toString().trim().replaceAll("-", "");
        }
        req.setAttribute("request_id",request_id);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
