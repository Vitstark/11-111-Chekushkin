package com.example.app.servlets.concert;        /**
 * @author Vitaly Chekushkin
 */

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.app.models.Concert;
import com.example.app.models.Person;
import com.example.app.models.Presentation;
import com.example.app.models.Role;
import com.example.app.service.ConcertService;
import com.example.app.service.PeopleService;
import com.example.app.service.PresentationService;

@WebServlet("/concerts")
@MultipartConfig
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
			request.setAttribute("person", person);

			if (person.getRole().equals(Role.ADMIN)) {
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
			Long conId = Long.valueOf(concertId);

			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");

			LocalDate currentDate;

			if (year != null && month != null && day != null) {
				currentDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
			} else {
				currentDate = LocalDate.now();
			}

			setDays(currentDate, request);

			List<Presentation> presentations = presentationService.findByConcertAndDateOrderByDate(conId, currentDate);
			Concert concert = concertService.findById(Long.valueOf(concertId));

			request.setAttribute("concert", concert);
			request.setAttribute("presentations", presentations);

			getServletContext()
				.getRequestDispatcher("/WEB-INF/views/concert.jsp")
				.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		Part part = request.getPart("photo");

		Concert concert = Concert.builder()
			.title(title)
			.author(author)
			.description(description)
			.part(part)
			.build();


		concertService.save(concert);

		response.sendRedirect(request.getContextPath() + "/concerts?id="
							  + concert.getId());
	}

	private void setDays(LocalDate date, HttpServletRequest request) {
		request.setAttribute("nextDate", date.plusDays(1));
		request.setAttribute("prevDate", date.minusDays(1));
	}
}
