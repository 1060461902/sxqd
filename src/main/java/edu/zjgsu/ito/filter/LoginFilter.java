package edu.zjgsu.ito.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取上一个请求URL
        String url = request.getRequestURI();


//        这四个目录下的问题过滤掉
        if (url.contains("/js") || url.contains("/fonts")
                || url.contains("/css") || url.contains("/images")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("发送请求的url为: " + url);

        //url指向登录界面
            if (url.contains("login")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 如果登录标识loginFlag符合要求不返回登录页面
            if (request.getSession().getAttribute("loginFlag") != null) {
                if (Integer.valueOf(request.getSession()
                        .getAttribute("loginFlag").toString()) == 1) {
                    filterChain.doFilter(request, response);
                    return;
                }
            } else {
                //重定向
                response.sendRedirect("/fieldManagement/admin-login.html");
                return;
            }
        return;
    }

    public void destroy() {

    }

}
