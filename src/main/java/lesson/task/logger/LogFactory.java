package lesson.task.logger;

import javax.servlet.ServletContextEvent;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * @author Oleg_Chapurin
 */
public class LogFactory implements Log{
    private static Log log;
    private static LogApplication logApplication;

    private LogFactory(){

    }

    public synchronized static Log getInstance(String nameLogger, ServletContextEvent servletContextEvent){
        if(log == null) {
            log = new LogFactory();
            switch (nameLogger) {
                case ("log4j"):
                    try {
                        logApplication = new LogApplicationLog4j("LogApplication",servletContextEvent);
                    } catch (MalformedURLException | URISyntaxException e) {
                        e.printStackTrace();
                        logApplication = new LogApplicationJul();
                    }
                    break;
                default:
                    logApplication = new LogApplicationJul();
            }
        }
        return log;
    }

    @Override
    public void debug(String message){
        logApplication.debugLogger(message);
    }

    @Override
    public void error(String message){
        logApplication.errorLogger(message);
    }

    @Override
    public void info(String message){
        logApplication.infoLogger(message);
    }

    @Override
    public void fatal(String message){
        logApplication.fatalLogger(message);
    }
}
