package com.example.app.servlets.concert;        /**
 * @author Vitaly Chekushkin
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/concerts/new")
public class AddConcertServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/addConcert.jsp")
            .forward(request, response);
	}
}
