package lesson.task.service;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author Oleg_Chapurin
 *
 */
public interface HandlerRequest {
    /**
     *
     * @param servletRequest ServletRequest
     * @param servletResponse ServletResponse
     * @throws ServletException
     * @throws IOException
     */
    void handler(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;
}
