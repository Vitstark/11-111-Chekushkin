package com.example.app.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    private final List paths = List.of("/mypage", "/change_person");
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String resource = req.getRequestURI().substring(getServletContext().getContextPath().length());
        Long sessionId = (Long) req.getSession().getAttribute("id");
        if (paths.contains(resource) && sessionId == null) {
            res.sendRedirect(getServletContext().getContextPath() + "/login");
        } else {
            chain.doFilter(req, res);
        }
    }
}
