package com.example.app.servlets.presentation;        /**
 * @author Vitaly Chekushkin
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/presentation/new")
public class NewPresentationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setAttribute("concertId", request.getParameter("concertId"));
        request.getRequestDispatcher("/WEB-INF/views/newPresentation.jsp").forward(request, response);
	}
}
