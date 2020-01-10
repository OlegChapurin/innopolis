package lesson.task.service;


import lesson.task.dao.UserSqlRequest;
import lesson.task.entity.*;
import lesson.task.logger.Log;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg_Chapurin
 */
public abstract class ChainLink implements Node {
    protected UserSqlRequest<Visitor, DateObject> userSqlRequest;
    protected FactoryVisitor<Visitor, DateObject> factoryVisitor;
    protected Log log;
    protected Node nextNode;

    /**
     * Receive an object by id
     *
     * @param id - id object in database
     */
    protected void receiveUser(int id, HttpSession session) {
        Visitor user = userSqlRequest.getUser(id);
        session.setAttribute("visitor", user);
    }

    /**
     * Creates an object with parameter values from a query
     *
     * @param servletRequest ServletRequest
     * @return object
     */
    protected Visitor creatUser(ServletRequest servletRequest) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", servletRequest.getParameter("name"));
        hashMap.put("login", servletRequest.getParameter("login"));
        hashMap.put("password", servletRequest.getParameter("password"));
        hashMap.put("email", servletRequest.getParameter("mail"));
        ArrayList<DateObject> telephones = new ArrayList<>();
        DateObject dateObject = new Telephone();
        dateObject.addEditObject(servletRequest.getParameter("telephone"));
        dateObject.addEditStatusObject(3);
        telephones.add(dateObject);
        Visitor user = factoryVisitor.getVisitor("Users", hashMap, telephones);
        return user;
    }

    /**
     * Add value parameter telephone in object
     *
     * @param servletRequest ServletRequest
     */
    protected void addTelephone(ServletRequest servletRequest, HttpSession session) {
        String telephone = servletRequest.getParameter("telephone");
        ((Visitor) session.getAttribute("visitor")).setTelephone(telephone);
        ((Visitor) session.getAttribute("visitor")).setStatus(true);
    }

    /**
     * Delete value parameter telephone of object
     *
     * @param servletRequest ServletRequest
     */
    protected void deleteTelephone(ServletRequest servletRequest, HttpSession session) {
        int index = Integer.valueOf(servletRequest.getParameter("deleteTelephone"));
        ((Visitor) session.getAttribute("visitor")).markDeleteTelephone(index);
        ((Visitor) session.getAttribute("visitor")).setStatus(true);
    }

    /**
     * Changes the value fields
     *
     * @param servletRequest ServletRequest
     */
    protected void editFields(ServletRequest servletRequest, HttpSession session) {
        Class<? extends Visitor> userClass = ((Visitor) session.getAttribute("visitor")).getClass();
        Field[] fields = userClass.getDeclaredFields();
        Map<String, String[]> parameters = (Map<String, String[]>) servletRequest.getParameterMap();
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            Field field = fields[i];
            String[] parameter = parameters.get(field.getName());
            if (parameter != null) {
                field.setAccessible(true);
                String classType = field.getType().getName();
                if ("java.lang.String".equals(classType)) {
                    try {
                        field.set(((Visitor) session.getAttribute("visitor")), parameter[0]);
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }
                if ("java.lang.int".equals(classType)) {
                    try {
                        field.setInt(((Visitor) session.getAttribute("visitor")), Integer.valueOf(parameter[0]));
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }
                ((Visitor) session.getAttribute("visitor")).setStatus(true);
            }
        }
    }

    /**
     * Changes the value object
     *
     * @param servletRequest ServletRequest
     */
    protected void editUser(ServletRequest servletRequest, HttpSession session) {
        if (servletRequest.getParameter("deleteTelephone") != null) {
            deleteTelephone(servletRequest, session);
        } else if (servletRequest.getParameter("addTelephone") != null) {
            addTelephone(servletRequest, session);
        } else {
            editFields(servletRequest, session);
        }

    }

    /**
     * Add event handle
     * @param node form html event handle
     */
    @Override
    public void addNode(Node node) {
        nextNode = node;
    }

    /**
     *
     * @param servletRequest ServletRequest
     * @param servletResponse ServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public abstract void handler(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException;

    /**
     *
     * @param userSqlRequest DAO for object Visitor
     * @param factoryVisitor Factory object Visitor
     */
    @Override
    public abstract void addResource(UserSqlRequest<Visitor, DateObject> userSqlRequest, FactoryVisitor<Visitor,
            DateObject> factoryVisitor, Log log);
}
