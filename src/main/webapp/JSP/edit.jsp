<%@ page import="lesson.task.entity.DateObject" %>
<%@ page import="lesson.task.entity.Visitor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
    <form id="idName" name="Edit" action="enter" method="post" style="width:220px">
        <div style="width:320px" align="right">
            <label>Name
                <input name="editObject" type="hidden">
                <input name="name" type="text" id="name" maxlength="20"
                       value="<%= ((Visitor) session.getAttribute("visitor")).getName().toString()%>"/>
                <input type="submit" name="regName" id="eN" value="edit" style="width:40px"/>
                <br/>
            </label>
        </div>
    </form>
    <form id="idLogin" name="Edit" action="enter" method="post" style="width:220px">
        <div style="width:320px" align="right">
            <label>Login
                <input name="editObject" type="hidden">
                <input name="login" type="text" id="login" maxlength="20"
                       value="<%=((Visitor) session.getAttribute("visitor")).getLogin().toString()%>"/>
                <input type="submit" name="regLogin" id="eL" value="edit" style="width:40px"/>
                <br/>
            </label>
        </div>
    </form>
    <form id="idPassword" name="Edit" action="enter" method="post" style="width:220px">
        <div style="width:320px" align="right">
            <label>Password
                <input name="editObject" type="hidden">
                <input name="password" type="text" id="password" maxlength="10"
                       value="<%=((Visitor) session.getAttribute("visitor")).getPassword().toString()%>"/>
                <input type="submit" name="regPassword" id="eP" value="edit" style="width:40px"/>
                <br/>
            </label>
        </div>
    </form>
    <form id="idMail" name="Edit" action="enter" method="post" style="width:220px">
        <div style="width:320px" align="right">
            <label>E-mail
                <input name="editObject" type="hidden">
                <input name="email" type="text" id="mail" maxlength="20"
                       value="<%=((Visitor) session.getAttribute("visitor")).getEmail().toString()%>"/>
                <input type="submit" name="regMail" id="eM" value="edit" style="width:40px"/>
                <br/>
            </label>
        </div>
    </form>

    <c:set var="nambers" scope="session" value="${pageContext.session.getAttribute('visitor').getTelephone()}"/>
    <c:set var="counter" scope="session" value="${0}"/>
    <c:forEach var="namber" items="${nambers}">
        <c:if test="${namber.getStatusObject() != 2}">
        <form id="telephone${counter}" name="Edit" action="enter" method="post" style="width:220px">
            <div style="width:320px" align="right">
                <label>Телефон
                    <input name="editObject" type="hidden">
                    <input name="deleteTelephone" type="hidden" value="${counter}">
                    <input name="telephone${counter}" type="text" id="id${counter}" maxlength="20"
                           value="${namber.getObject()}"/>
                    <input type="submit" name="edit" id="${counter}" value="-" style="width:40px"/>
                    <br/>
                </label>

            </div>
        </form>
         </c:if>
        <c:set var="counter" scope="session" value="${counter + 1}"/>
    </c:forEach>

    <form id="idAddTelephone" name="Edit" action="enter" method="post" style="width:220px">
        <div style="width:320px" align="right">
            <label>Телефон
                <input name="editObject" type="hidden">
                <input name="telephone" type="text" id="${counter}" maxlength="20" value="${namber}"/>
                <input type="submit" name="addTelephone" id="${counter}" value="+" style="width:40px"/>
                <br/>
                <br/>
            </label>
        </div>
    </form>

        <form id="submit" name="Edit" action="enter" method="post" style="width:220px">
            <div style="width:350px" align="right">
                <p>
                    <label>
                        <input type="submit" name="exit" id="exit" value="Выйте"/>
                        <input type="submit" name="back" id="back" value="Отмена"/>
        <c:if test="${pageContext.session.getAttribute('visitor').getStatus()}">
                        <input type="submit" name="editUser" id="enterID" value="Записать"/>
        </c:if>
                    </label>
                </p>

            </div>
        </form>

</div>
