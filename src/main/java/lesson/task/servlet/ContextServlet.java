package lesson.task.servlet;

import lesson.task.connection.ConnectionDb;
import lesson.task.connection.PoolConnections;
import lesson.task.dao.FactorySql;
import lesson.task.dao.FactorySqlUser;
import lesson.task.entity.DateObject;
import lesson.task.entity.FactoryUser;
import lesson.task.entity.FactoryVisitor;
import lesson.task.entity.Visitor;
import lesson.task.logger.Log;
import lesson.task.logger.LogFactory;
import lesson.task.service.HandlerRequestFactory;
import lesson.task.service.HandlerRequestInstance;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Oleg_Chapurin
 */
@WebListener
public class ContextServlet implements ServletContextListener {
    private ConnectionDb connectionDb;
    private FactorySqlUser factorySqlUser;
    private FactoryVisitor<Visitor, DateObject> factoryVisitor;
    private HandlerRequestFactory handlerRequestFactory;
    private Log log;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log = LogFactory.getInstance("log4j",servletContextEvent);
        String driver = servletContextEvent.getServletContext().getInitParameter("Driver");
        String url = servletContextEvent.getServletContext().getInitParameter("ConUrl");
        String user = servletContextEvent.getServletContext().getInitParameter("User");
        String password = servletContextEvent.getServletContext().getInitParameter("Password");
        String numberConnections = servletContextEvent.getServletContext().getInitParameter("NumberConnections");
        connectionDb = PoolConnections.getInstance();
        connectionDb.instancePoolConnection(driver,url,user,password,numberConnections);
        log.info("Instance pool connection database");
        factorySqlUser = FactorySql.getInstance();
        factoryVisitor = FactoryUser.getInstance();
        log.info("Instance FactoryVisitor");
        handlerRequestFactory = HandlerRequestInstance.getInstance(servletContextEvent);
        log.info("Instance HandlerRequestFactory");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connectionDb.Close();
        log.info("Close connection database");
    }
}
