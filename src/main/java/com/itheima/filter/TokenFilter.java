package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径
        String requestURI = request.getRequestURI();
        //判断路径是否是登录请求
        if(requestURI.equals("/login")){
            log.info("登录请求");
            filterChain.doFilter(request,response);
            return;
        }
        //获取请求头中的token
        String token = request.getHeader("token");
        //判断token是否存在，如果不存在，说明用户没有登陆，返回401错误信息
        if(token == null || token.equals("")){
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //存在，则进行校验，校验失败返回401错误信息
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //验证成功，放行
        log.info("令牌合法");
        filterChain.doFilter(request,response);
    }


}
