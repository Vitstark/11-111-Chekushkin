package com.example.app.servlets;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login");
    }
}