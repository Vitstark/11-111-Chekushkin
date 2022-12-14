package com.example.app.servlets;        /**
 * @author Vitaly Chekushkin
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateConcertServlet", value = "/CreateConcertServlet")
public class CreateConcertServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	}
}
