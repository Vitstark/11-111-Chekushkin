package com.example.app.servlets;        /**
 * @author Vitaly Chekushkin
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.example.app.models.Ticket;
import com.example.app.service.PresentationService;
import com.example.app.service.TicketService;

@WebServlet("/ticket/buy")
public class BuyTicketServlet extends HttpServlet {
	private TicketService ticketService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ticketService = (TicketService) config.getServletContext().getAttribute("ticketService");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Long userId = (Long) request.getSession().getAttribute("id");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		Ticket ticket = Ticket.builder()
			.presentationId(Long.valueOf(request.getParameter("presentationId")))
			.row(Integer.valueOf(request.getParameter("row")))
			.place(Integer.valueOf(request.getParameter("place")))
			.ownerId(userId)
			.build();
		
		ticketService.update(ticket);

		response.sendRedirect(request.getContextPath() + "/concerts");
	}
}
