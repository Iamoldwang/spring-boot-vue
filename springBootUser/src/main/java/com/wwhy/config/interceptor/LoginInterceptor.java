package com.wwhy.config.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wwhy.common.Contant;
import com.wwhy.common.result.CommResult;
import com.wwhy.common.util.StringUtil;
import com.wwhy.service.RedisService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        //swagger
        if (requestURI.startsWith("/user/swagger") || requestURI.startsWith("/user/v2")) {
            return true;
        }
        //注册
        if (requestURI.startsWith("/user/student/register")) {
            return true;
        }
        //登录
        if (requestURI.startsWith("/user/student/studentLogin") || requestURI.startsWith("/user/teacher/teacherLogin")) {
            return true;
        }

        String token = request.getHeader("token");

        if (StringUtil.isEmpty(token)) {
            returnJson(response, JSONObject.fromObject(CommResult.error(Contant.RESULT_UNAUTHORIZED, Contant.RESULT_UNAUTHORIZED_TEXT)).toString());
            return false;
        }

        Object user = redisService.get(token);
        if (user != null) {
            redisService.expire(token, Contant.TOKEN_SESSION_LIVE_TIME);//重新设置token有效期
            return true;
        } else {
            returnJson(response, JSONObject.fromObject(CommResult.error(Contant.RESULT_UNAUTHORIZED, Contant.RESULT_UNAUTHORIZED_TEXT)).toString());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (Exception e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
