package com.example.app.servlets;

import com.example.app.service.PeopleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/people")
public class PeopleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeopleServiceImpl peopleService = (PeopleServiceImpl) getServletContext().getAttribute("peopleService");

        request.setAttribute("people", peopleService.findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/views/people.jsp").forward(request, response);
    }
}