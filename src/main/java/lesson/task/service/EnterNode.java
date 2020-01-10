package lesson.task.service;

import lesson.task.dao.UserSqlRequest;
import lesson.task.entity.DateObject;
import lesson.task.entity.FactoryVisitor;
import lesson.task.entity.Visitor;
import lesson.task.logger.Log;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Oleg_Chapurin
 */
public class EnterNode extends ChainLink {
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
        if(servletRequest.getParameter("enter") != null){
            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");
            if(((boolean) session.getAttribute("authentication"))){
                session.setAttribute("typePage", TypePage.ENTER.getTypePage());
            }else {
                int id = userSqlRequest.getIdUser(login, password);
                if (id > 0) {
                    receiveUser(id,session);
                    session.setAttribute("id", id);
                    session.setAttribute("authentication", true);
                    session.setAttribute("typePage", TypePage.ENTER.getTypePage());
                } else {
                    session.setAttribute("typePage", TypePage.REG.getTypePage());
                }
            }
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
