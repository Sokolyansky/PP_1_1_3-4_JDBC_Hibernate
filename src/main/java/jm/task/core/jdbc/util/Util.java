package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dialect = "org.hibernate.dialect.MySQLDialect";
    private static final String url = "jdbc:mysql://localhost:3306/Users";
    private static final String user = "root";
    private static final String password = "root1";

    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;

    private Util() {
    }

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null)
            return sessionFactory;

        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", dialect);
            configuration.setProperty("hibernate.connection.driver_class", driver);
            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", user);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;

        } catch (Exception e) {
            throw new RuntimeException("There was an error building the session factory", e);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeSession() {
        sessionFactory.close();
    }

}
