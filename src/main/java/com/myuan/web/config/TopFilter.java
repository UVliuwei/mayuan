package com.myuan.web.config;

import com.myuan.web.service.PostService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @author liuwei
 * @date 2018/2/26 15:33
 * 热搜session
 */
@Component
@WebFilter(urlPatterns = {"/jie/**", "/", "/index", "/column/**"})
public class TopFilter implements Filter {

    @Autowired
    private PostService postService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest re = (HttpServletRequest) request;
        request.setAttribute("topList", postService.getTopPost());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
