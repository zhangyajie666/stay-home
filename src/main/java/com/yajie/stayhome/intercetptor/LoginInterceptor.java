package com.yajie.stayhome.intercetptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lenovo
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    /**
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        if (request.getRequestURI().startsWith("/admin-") &&
                request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("/admin-login");
        }
        if (request.getRequestURI().startsWith("/book_") &&
                request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
        }
        if (request.getRequestURI().startsWith("/owner-") &&
                request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
        }
        if (request.getRequestURI().startsWith("/order_") &&
                request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
        }
        return true;
    }

}