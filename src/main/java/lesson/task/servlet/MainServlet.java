package lesson.task.servlet;


import lesson.task.logger.Log;
import lesson.task.service.HandlerRequest;
import lesson.task.service.HandlerRequestFactory;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Oleg_Chapurin
 */
@WebServlet("/")
public class MainServlet extends GenericServlet {
    @Inject
    private HandlerRequestFactory handlerRequestFactory;
    @Inject
    private Log log;
    private HandlerRequest handlerRequest;

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        if (((HttpServletRequest) servletRequest).getSession(true).isNew()) {
            handlerRequest = handlerRequestFactory.getHandlerRequest();
            log.info("New session ip ".concat(servletRequest.getLocalAddr()));
        }
        handlerRequest.handler(servletRequest, servletResponse);
    }
}
