package lesson.task.logger;

import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Oleg_Chapurin
 */
public class LogApplicationJul implements LogApplication {
    private Logger logger;

    public LogApplicationJul() {
        this.logger = Logger.getLogger("Jul");
    }

    public LogApplicationJul(Logger logger) {
        this.logger = logger;
    }

    public LogApplicationJul(ServletContextEvent servletContextEvent) {
        try {
            LogManager.getLogManager().readConfiguration(
                    servletContextEvent.getServletContext().getResourceAsStream("WEB-INF/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.logger = Logger.getLogger("Jul");
    }

    @Override
    public void errorLogger(String message) {
        this.logger.severe(message);
    }

    @Override
    public void infoLogger(String message) {
        this.logger.info(message);
    }

    @Override
    public void debugLogger(String message) {
        this.logger.fine(message);
    }

    @Override
    public void fatalLogger(String message) {
        this.logger.severe(message);
    }
}
