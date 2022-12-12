package com.example.app.listeners;

import com.example.app.repositories.*;
import com.example.app.service.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.example.app.util.encoders.PasswordHashCodeEncoder;
import com.example.app.util.validators.EmailValidator;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final String DB_PROPERTIES_PATH = "/WEB-INF/db.properties";
    private static final String IMAGE_PATH = "/images";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = initDataSource(sce.getServletContext()
            .getResourceAsStream(DB_PROPERTIES_PATH));

        PeopleRepository peopleRepository = new PeopleRepositoryJdbcImpl(dataSource);
        ConcertRepository concertRepository =
            new ConcertRepositoryJdbcTemplateImpl(dataSource);
        PresentationRepository presentationRepository =
            new PresentationRepositoryJdbcTemplateImpl(dataSource);
        TicketRepository ticketRepository =
            new TicketRepositoryJdbcTemplateImpl(dataSource);

        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository,
            ticketRepository, new PasswordHashCodeEncoder());
        ConcertService concertService = new ConcertServiceImpl(concertRepository,
            presentationRepository, sce.getServletContext()
                                        .getRealPath("") + File.pathSeparator + IMAGE_PATH);
        PresentationService presentationService = new PresentationServiceImpl(
            presentationRepository, ticketRepository, concertRepository);
        TicketService ticketService = new TicketServiceImpl(ticketRepository,
            presentationRepository, peopleRepository);

        EmailValidator emailValidator = new EmailValidator(peopleService);

        sce.getServletContext().setAttribute("peopleService", peopleService);
        sce.getServletContext().setAttribute("concertService", concertService);
        sce.getServletContext().setAttribute("presentationService", presentationService);
        sce.getServletContext().setAttribute("ticketService", ticketService);
        sce.getServletContext().setAttribute("emailValidator", emailValidator);
    }

    private DataSource initDataSource(InputStream is) {
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String uri = properties.getProperty("db.uri");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");

        try {
            Class.forName(driver).newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        return new DriverManagerDataSource(uri, username, password);
    }
}