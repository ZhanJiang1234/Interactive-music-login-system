package com.project.music.common;


import cn.hutool.core.util.StrUtil;
import com.project.music.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        String requestURI = httpRequest.getRequestURI();
        

        boolean isExcludeEndpoint = exclude(requestURI);

        if (isExcludeEndpoint) {
            chain.doFilter(request, response);
        } else {
            boolean isLoggedIn = ServletUtils.checkIfLoggedIn(httpRequest);

            if (isLoggedIn) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(ServletUtils.getCtxPath(httpRequest)+"/login");
            }
        }
    }

    
    private boolean exclude(String requestURI) {
        return StrUtil.containsAny(requestURI, "login", "register", "/styles", "/js", "/images","/index", "/file") || requestURI.equals("/");
    }
    
    @Override
    public void destroy() {
       
    }

    

}
