package lesson.task.logger;

/**
 * @author Oleg_Chapurin
 */
public interface LogApplication {
    void errorLogger(String message);
    void infoLogger(String message);
    void debugLogger(String message);
    void fatalLogger(String message);
}
