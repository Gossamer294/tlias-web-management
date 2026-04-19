package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求路径
        String requestURI = request.getRequestURI();
        //判断路径是否是登录请求
        if(requestURI.equals("/login")){
            log.info("登录请求");

            return  true;
        }
        //获取请求头中的token
        String token = request.getHeader("token");
        //判断token是否存在，如果不存在，说明用户没有登陆，返回401错误信息
        if(token == null || token.equals("")){
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //存在，则进行校验，校验失败返回401错误信息
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //验证成功，放行
        log.info("令牌合法");
        return true;
    }
}

