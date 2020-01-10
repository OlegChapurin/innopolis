package lesson.task.service;

import lesson.task.dao.UserSqlRequest;
import lesson.task.entity.DateObject;
import lesson.task.entity.FactoryVisitor;
import lesson.task.entity.Visitor;
import lesson.task.logger.Log;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Oleg_Chapurin
 */
public class EditUserNode extends ChainLink {
    /**
     *
     * @param servletRequest ServletRequest
     * @param servletResponse ServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void handler(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
        if(servletRequest.getParameter("editUser") != null) {
            userSqlRequest.editUser((Visitor) session.getAttribute("visitor"));
            int id = ((Visitor) session.getAttribute("visitor")).getId();
            session.removeAttribute("visitor");
            receiveUser(id,session);
            session.setAttribute("typePage", TypePage.ENTER.getTypePage());
            servletRequest.getRequestDispatcher("JSP/title.jsp").forward(servletRequest,servletResponse);
        }else {
            if (nextNode != null){
                nextNode.handler(servletRequest, servletResponse);
            }else {
                servletRequest.getRequestDispatcher("JSP/title.jsp").forward(servletRequest,servletResponse);
            }
        }
    }

    /**
     *
     * @param userSqlRequest DAO for object Visitor
     * @param factoryVisitor Factory object Visitor
     */
    @Override
    public void addResource(UserSqlRequest<Visitor, DateObject> userSqlRequest,
                            FactoryVisitor<Visitor, DateObject> factoryVisitor, Log log) {
        super.userSqlRequest = userSqlRequest;
        super.factoryVisitor = factoryVisitor;
        super.log = log;
    }
}
