package com.example.app.servlets.auth;

import com.example.app.models.Person;
import com.example.app.service.PeopleServiceImpl;
import com.example.app.util.encoders.Encoder;
import com.example.app.util.encoders.PasswordHashCodeEncoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login_process")
public class ProcessLoginServlet extends HttpServlet {
    private Encoder passwordEncoder;

    @Override
    public void init() throws ServletException {
        super.init();
        passwordEncoder = new PasswordHashCodeEncoder();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeopleServiceImpl peopleService = (PeopleServiceImpl) getServletContext().getAttribute("peopleService");

        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        if (email == null || email.isEmpty()) {
            request.setAttribute("emailError", "Email can`t be blank");
            forwardToLogin(request, response);
            return;
        }

        Optional<Person> personFromDB = peopleService.findByEmail(email);

        if (personFromDB.isEmpty()) {
            request.setAttribute("emailError", "Email isn`t exist");
            forwardToLogin(request, response);
            return;
        } else if (!personFromDB.get().getPassword().equals(passwordEncoder.encode(password))) {
            request.setAttribute("passwordError", "Uncorrect password");
            forwardToLogin(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", personFromDB.get().getId());

        response.sendRedirect(getServletContext().getContextPath() + "/concerts");
    }

    private void forwardToLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }
}
