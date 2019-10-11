package com.crAdmin.module.index.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {

    private static final String[] notFilter = new String[] {"/cr-admin","/cr-admin/","css","js","jpg","png","login.do"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException,
            IOException {

        String uri = request.getRequestURI();
        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.endsWith(s)) {
                doFilter = false;
                break;
            }
        }
        if (doFilter) {
            Object obj = request.getSession().getAttribute("user");
            if (null == obj) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                String loginPage = "/cr-admin";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("window.top.location.href='");
                builder.append(loginPage);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
