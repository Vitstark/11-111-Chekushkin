package com.example.app.servlets.concert;        /**
 * @author Vitaly Chekushkin
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.example.app.models.Concert;
import com.example.app.models.Person;
import com.example.app.models.Presentation;
import com.example.app.models.Role;
import com.example.app.service.ConcertService;
import com.example.app.service.PeopleService;
import com.example.app.service.PresentationService;

@WebServlet("/concerts")
public class ConcertsServlet extends HttpServlet {
	private ConcertService concertService;
	private PeopleService peopleService;
	private PresentationService presentationService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		concertService = (ConcertService) config.getServletContext()
			.getAttribute("concertService");
		peopleService = (PeopleService) config.getServletContext()
			.getAttribute("peopleService");
		presentationService = (PresentationService) config.getServletContext()
			.getAttribute("presentationService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Long id = (Long) request.getSession().getAttribute("id");

		if (id != null) {
			Person person = peopleService.findById(id);

			if (person.getRole().equals(Role.ADMIN)) {
				System.out.println(person.getRole());
				request.setAttribute("isAdmin", true);
			}
		}

		String concertId = request.getParameter("id");

		if (concertId == null || concertId.isBlank()) {
			List<Concert> concerts = concertService.findAll();
			request.setAttribute("concerts", concerts);

			getServletContext()
				.getRequestDispatcher("/WEB-INF/views/concerts.jsp")
				.forward(request, response);
		} else {
			Concert concert = concertService.findById(Long.valueOf(concertId));
			request.setAttribute("concert", concert);
			request.setAttribute("presentation", concert.getPresentations());

			getServletContext()
				.getRequestDispatcher("/WEB-INF/views/concert.jsp")
				.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		Concert concert = Concert.builder()
			.title(title)
			.description(description)
			.build();

		concertService.save(concert);

		response.sendRedirect(request.getContextPath() + "/concerts?id="
							  + concert.getId());
	}
}
