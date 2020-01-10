<%@ page import="lesson.task.entity.DateObject" %>
<%@ page import="lesson.task.entity.Visitor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
    <div align="center">Ваши учетные данные</div>

    <div align="center">
        <table width="300" border="1">
            <tr>
                <td width="50">Name</td>
                <td><%= ((Visitor) session.getAttribute("visitor")).getName().toString()%></td>
            </tr>
            <tr>
                <td>&nbsp;Login</td>
                <td>&nbsp;<%=((Visitor) session.getAttribute("visitor")).getLogin().toString()%></td>
            </tr>
            <tr>
                <td>&nbsp;Password</td>
                <td>&nbsp;<%=((Visitor) session.getAttribute("visitor")).getPassword().toString()%></td>
            </tr>
            <tr>
                <td>&nbsp;Mail</td>
                <td>&nbsp;<%=((Visitor) session.getAttribute("visitor")).getEmail().toString()%></td>
            </tr>
            <c:set var="nambers" scope="session" value="${pageContext.session.getAttribute('visitor').getTelephone()}" />
            <c:forEach var="namber" items="${nambers}" >
                <c:if test="${namber.getStatusObject() != 2}">
                <tr>
                    <td>Телефон</td>
                    <td><c:out value="${namber.getObject()}"/></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
        <form id="enterForm" name="enterForm" method="post" action="enter" style="width:240px">
            <p>
                <label>
                    <input type="submit" name="exit" id="exit" value="Выйте"/>
                    <input type="submit" name="edit" id="editID" value="Изменить данные" />
                </label>
            </p>
        </form>
    </div>
</div>