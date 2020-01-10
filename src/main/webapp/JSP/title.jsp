<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>lesson 22-27</title>
</head>
<body>
<c:if test="${'ZERO'.equals(pageContext.session.getAttribute('typePage')) == true}">
<jsp:include page="zero.jsp" flush="false" />
</c:if>
<c:if test="${('ENTER'.equals(pageContext.session.getAttribute('typePage')) == true) &&
 (pageContext.session.getAttribute('authentication') == true)}">
  <jsp:include page="enter.jsp" flush="false" />
</c:if>
<c:if test="${('REG'.equals(pageContext.session.getAttribute('typePage')) == true) &&
(pageContext.session.getAttribute('authentication') == false)}">
    <jsp:include page="reg.jsp" flush="false" />
</c:if>
<c:if test="${('EDIT'.equals(pageContext.session.getAttribute('typePage')) == true) &&
(pageContext.session.getAttribute('authentication') == true)}">
    <jsp:include page="edit.jsp" flush="false" />
</c:if>
</body>
</html>
