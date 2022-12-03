package com.example.app.servlets;

import com.example.app.models.Person;
import com.example.app.service.PeopleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeopleService peopleService = (PeopleService) getServletContext().getAttribute("peopleService");

        Long id = (Long) request.getSession().getAttribute("id");
        Person person = peopleService.findById(id);

        request.setAttribute("person", person);
        getServletContext().getRequestDispatcher("/WEB-INF/views/mypage.jsp").forward(request, response);
    }
}
