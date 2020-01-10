package lesson.task.service;

import lesson.task.dao.UserSqlRequest;
import lesson.task.entity.DateObject;
import lesson.task.entity.FactoryVisitor;
import lesson.task.entity.Visitor;
import lesson.task.logger.Log;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Oleg_Chapurin
 */
public class HandlerRequestInstance implements HandlerRequestFactory {
    @Inject
    private UserSqlRequest<Visitor, DateObject> userSqlRequest;
    @Inject
    private FactoryVisitor<Visitor, DateObject> factoryVisitor;
    @Inject
    private Log log;
    private static HandlerRequestInstance handlerRequestInstance;
    private static ServletContextEvent servletContextEvent;
    private ArrayList<Node> chainLink = new ArrayList<>();
    private ArrayList<String> arrayParameters = new ArrayList<>();

    private HandlerRequestInstance(){

    }

    /**
     * Аorms a list of handlers
     * @param servletContextEvent ServletContextEvent
     */
    private void readProperties(ServletContextEvent servletContextEvent){
        Properties properties = new Properties();
        try {
            properties.load(servletContextEvent.getServletContext().getResourceAsStream("WEB-INF/config.properties"));
            Enumeration parameters = properties.propertyNames();
            while (parameters.hasMoreElements()){
                arrayParameters.add((String) properties.get(parameters.nextElement()));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /***
     * Instance object handler
     */
    private void instanceChainLink() {
        for (String parameter:arrayParameters) {
            try {
                Class<Node> classNode = (Class<Node>) getClass().getClassLoader().loadClass(parameter);
                Node node = classNode.newInstance();
                node.addResource(userSqlRequest,factoryVisitor,log);
                chainLink.add(node);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }
    }

    public static HandlerRequestFactory getInstance(ServletContextEvent servletContext){
        if (handlerRequestInstance == null){
            servletContextEvent = servletContext;
            handlerRequestInstance = new HandlerRequestInstance();
        }
        return handlerRequestInstance;
    }

    /**
     *
     * @return new object with chained handlers
     */
    @Override
    public HandlerRequest getHandlerRequest() {
        if (chainLink.size() == 0){
            readProperties(servletContextEvent);
            instanceChainLink();
        }
        HandlerService handlerService = new HandlerService();
        for (Node node:chainLink) {
            handlerService.addNode(node);
        }
        return handlerService;
    }
}
