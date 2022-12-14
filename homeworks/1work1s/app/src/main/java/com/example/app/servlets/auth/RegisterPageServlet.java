package com.example.app.servlets.auth;

import com.example.app.models.Person;
import com.example.app.models.Role;
import com.example.app.service.PeopleService;
import com.example.app.util.exceptions.ValidationException;
import com.example.app.util.validators.NameValidator;
import com.example.app.util.validators.PasswordValidator;
import com.example.app.util.validators.Validator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterPageServlet extends HttpServlet {
    private Validator emailValidator;
    private Validator nameValidator;
    private Validator passwordValidator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        nameValidator = new NameValidator();
        passwordValidator = new PasswordValidator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        emailValidator = (Validator) getServletContext().getAttribute("emailValidator");

        Person newPerson = Person.builder()
                .email(request.getParameter("email"))
                .name(request.getParameter("name"))
                .password(request.getParameter("password"))
                .role(Role.USER)
                .build();

        if (!isCorrectPerson(newPerson, request)) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }

        PeopleService peopleService = (PeopleService) getServletContext().getAttribute("peopleService");
        peopleService.save(newPerson);

        request.getSession().setAttribute("id", newPerson.getId());
        response.sendRedirect(request.getContextPath() + "/concerts");
    }

    private boolean isCorrectPerson(Person person, HttpServletRequest req) {
        boolean isCorrect = true;
        isCorrect = isCorrect & isCorrectField(emailValidator, person.getEmail(), "emailError", req);
        isCorrect = isCorrect & isCorrectField(nameValidator, person.getName(), "nameError", req);
        isCorrect = isCorrect & isCorrectField(passwordValidator, person.getPassword(), "passwordError", req);
        return isCorrect;
    }

    private boolean isCorrectField(Validator validator, Object field, String errorName, HttpServletRequest req) {
        try {
            validator.validate(field);
        } catch (ValidationException e) {
            req.setAttribute(errorName, e.getMessage());
            return false;
        }
        return true;
    }
}
