package lesson.task.service;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Handles the request
 * @author Oleg_Chapurin
 */
public class HandlerService implements HandlerRequest {
    private Node first;
    private Node last;

    private void instanceNodes(Node node){
        this.first = node;
        this.last = node;
    }
    public HandlerService() {

    }

    public HandlerService(Node node) {
        instanceNodes(node);
    }

    public void addNode(Node node) {
        if (this.first == null) {
            instanceNodes(node);
        }else {
            this.last.addNode(node);
            this.last = node;
        }
    }

    /**
     * @param servletRequest  ServletRequest
     * @param servletResponse ServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void handler(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if (this.first != null) {
            this.first.handler(servletRequest, servletResponse);
        }else {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
            session.setAttribute("authentication", false);
            session.setAttribute("typePage", TypePage.ZERO.getTypePage());
        }
    }
}
