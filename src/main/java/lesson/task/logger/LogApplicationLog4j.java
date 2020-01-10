package lesson.task.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import javax.servlet.ServletContextEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Oleg_Chapurin
 */
public class LogApplicationLog4j implements LogApplication {
    private Logger logger;

    public LogApplicationLog4j(String nameLogger) {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        loggerContext.setConfigLocation(URI.create("file://WEB-INF/log4j2.xml"));
        this.logger = LogManager.getLogger(nameLogger);

    }

    public LogApplicationLog4j(String nameLogger, ServletContextEvent servletContextEvent)
            throws MalformedURLException, URISyntaxException {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        URI uri = servletContextEvent.getServletContext().getResource("WEB-INF/log4j2.xml").toURI();
        loggerContext.setConfigLocation(uri);
        this.logger = LogManager.getLogger(nameLogger);

    }

    public LogApplicationLog4j(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void errorLogger(String message){
        this.logger.error(message);
    }

    @Override
    public void infoLogger(String message){
        this.logger.info(message);
    }

    @Override
    public void debugLogger(String message){
        this.logger.debug(message);
    }

    @Override
    public void fatalLogger(String message){
        this.logger.fatal(message);
    }
}
