package com.example.app.servlets;

import com.example.app.models.Person;
import com.example.app.service.PeopleService;
import util.exceptions.ValidationException;
import util.validators.NameValidator;
import util.validators.Validator;

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
        String newFirstName = request.getParameter("firstName");
        String newLastName = request.getParameter("lastName");

        PeopleService peopleService = (PeopleService) getServletContext().getAttribute("peopleService");
        Long id = (Long) request.getSession().getAttribute("id");
        Person person = peopleService.findById(id).get();

        if (!(newFirstName == null || newFirstName.isBlank())) {
            person.setFirstName(newFirstName);
        }
        if (!(newLastName == null || newLastName.isBlank())) {
            person.setLastName(newLastName);
        }

        if (!isCorrectNames(person, request)) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/changePerson.jsp").forward(request, response);
            return;
        }

        peopleService.update(person);

        response.sendRedirect(getServletContext().getContextPath() + "/mypage");
    }

    private boolean isCorrectNames(Person person, HttpServletRequest req) {
        boolean isCorrect = true;
        isCorrect = isCorrect & isCorrectName(person.getFirstName(), "firstNameError", req);
        isCorrect = isCorrect & isCorrectName(person.getLastName(), "lastNameError", req);
        return isCorrect;
    }

    private boolean isCorrectName(Object field, String errorName, HttpServletRequest req) {
        try {
            nameValidator.validate(field);
        } catch (ValidationException e) {
            req.setAttribute(errorName, e.getMessage());
            return false;
        }
        return true;
    }
}
