package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;



//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    @Override
    // 初始化，web服务器启动的时候执行，只执行一次
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化方法");
    }
//拦截到请求之后，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
log.info("执行方法");
filterChain.doFilter(servletRequest,servletResponse);
    }
//销毁，web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
       log.info("销毁方法");
    }
}
