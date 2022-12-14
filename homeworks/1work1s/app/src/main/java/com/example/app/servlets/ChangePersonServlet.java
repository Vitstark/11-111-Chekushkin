package com.example.app.servlets;

import com.example.app.models.Person;
import com.example.app.service.PeopleService;
import com.example.app.util.exceptions.ValidationException;
import com.example.app.util.validators.NameValidator;
import com.example.app.util.validators.Validator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/change_person")
public class ChangePersonServlet extends HttpServlet {
    private Validator nameValidator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        nameValidator = new NameValidator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/changePerson.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("name");

        PeopleService peopleService = (PeopleService) getServletContext().getAttribute("peopleService");
        Long id = (Long) request.getSession().getAttribute("id");
        Person person = peopleService.findById(id);

        if (!(newName == null || newName.isBlank())) {
            person.setName(newName);
        }

        if (!isCorrectName(person.getName(), request)) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/changePerson.jsp").forward(request, response);
            return;
        }

        peopleService.update(person);

        response.sendRedirect(getServletContext().getContextPath() + "/mypage");
    }

    private boolean isCorrectName(String nameField, HttpServletRequest req) {
        try {
            nameValidator.validate(nameField);
        } catch (ValidationException e) {
            req.setAttribute("nameError", e.getMessage());
            return false;
        }
        return true;
    }
}
