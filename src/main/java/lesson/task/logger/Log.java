package lesson.task.logger;

/**
 * @author Oleg_Chapurin
 */
public interface Log {
    void debug(String message);
    void error(String message);
    void info(String message);
    void fatal(String message);
}
