package com.example.app.servlets.presentation;        /**
 * @author Vitaly Chekushkin
 */

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.models.Presentation;
import com.example.app.models.Ticket;
import com.example.app.service.PresentationService;
import com.example.app.service.TicketService;

@WebServlet("/presentation")
public class PresentationsServlet extends HttpServlet {
    private PresentationService presentationService;
	private TicketService ticketService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        presentationService = (PresentationService) config.getServletContext()
            .getAttribute("presentationService");
		ticketService = (TicketService) config.getServletContext().getAttribute("ticketService");
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
		String rowParameter = request.getParameter("row");
		Integer row;

		if (rowParameter == null) {
			row = 1;
		} else {
			row = Integer.parseInt(rowParameter);
		}

		List<Ticket> tickets = ticketService.findAllByPresentationIdAndRow(id, row);

        request.setAttribute("presentation", presentationService.findById(id));
		request.setAttribute("tickets", tickets);
		request.setAttribute("nextRow", ++row);
		request.setAttribute("prevRow", --row);

        request.getServletContext()
            .getRequestDispatcher("/WEB-INF/views/presentation.jsp")
            .forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String method = request.getParameter("method").toLowerCase();
		if (Objects.equals("delete", method)) {
			doDelete(request, response);
			return;
		} else if (Objects.equals("put", method)) {
			doPut(request, response);
			return;
		}

		Long concertId = Long.valueOf(request.getParameter("concertId"));
		Timestamp presentationTime = Timestamp.valueOf(request.getParameter("presentationTime"));

		Presentation presentation = Presentation.builder()
			.concertId(concertId)
			.time(presentationTime)
			.build();

		presentationService.save(presentation);
		response.sendRedirect(request.getContextPath() + "presentations?id="
							  + presentation.getId());
	}

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
		Long presentationId = Long.valueOf(req.getParameter("id"));
		Presentation presentation = presentationService.findById(presentationId);

		System.out.println(presentation);

		presentationService.delete(presentationId);

		resp.sendRedirect(req.getContextPath() + "/concerts?id="
						  + presentation.getConcertId());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

		Presentation presentation = presentationService.findById(
			Long.valueOf(req.getParameter(req.getParameter("id"))));

		Timestamp newPresentationTime = Timestamp.valueOf(
			req.getParameter("presentationTime"));
		presentation.setTime(newPresentationTime);

		presentationService.update(presentation);
    }
}
