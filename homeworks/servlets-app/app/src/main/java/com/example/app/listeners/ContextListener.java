package com.example.app.listeners;

import com.example.app.repositories.PeopleRepository;
import com.example.app.repositories.PeopleRepositoryJdbcImpl;
import com.example.app.service.PeopleServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import util.encoders.PasswordHashCodeEncoder;
import util.validators.EmailValidator;

@WebListener
public class ContextListener implements ServletContextListener {
    private static String DB_PROPERTIES_PATH = "/WEB-INF/db.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = initDataSource(sce.getServletContext().getResourceAsStream(DB_PROPERTIES_PATH));
        PeopleRepository peopleRepository = new PeopleRepositoryJdbcImpl(dataSource);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, new PasswordHashCodeEncoder());

        EmailValidator emailValidator = new EmailValidator(peopleService);

        sce.getServletContext().setAttribute("peopleService", peopleService);
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