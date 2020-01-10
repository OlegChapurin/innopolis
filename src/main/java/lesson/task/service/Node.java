package lesson.task.service;

import lesson.task.dao.UserSqlRequest;
import lesson.task.entity.DateObject;
import lesson.task.entity.FactoryVisitor;
import lesson.task.entity.Visitor;
import lesson.task.logger.Log;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Form html event handle
 * @author Oleg_Chapurin
 */
public interface Node {
    /**
     * Add event handle
     * @param node form html event handle
     */
    void addNode(Node node);

    /**
     *
     * @param servletRequest ServletRequest
     * @param servletResponse ServletResponse
     * @throws ServletException
     * @throws IOException
     */
    void handler(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException;

    /**
     *
     * @param userSqlRequest DAO for object Visitor
     * @param factoryVisitor Factory object Visitor
     */
    void addResource(UserSqlRequest<Visitor, DateObject> userSqlRequest,
                     FactoryVisitor<Visitor,DateObject> factoryVisitor, Log log);
}
