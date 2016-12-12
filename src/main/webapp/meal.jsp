<%--
  Created by IntelliJ IDEA.
  User: Marisha
  Date: 12/12/16
  Time: 01:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Create or update a new meal</title>
</head>
<body>
<form method="POST" action="meals" name="frmCreateOrUpdateMeal">
    id : <input type="text" readonly="readonly" name="id"
                value="<c:out value="${meal.id}"/>"/> <br/>
    description : <input type="text" name="description"
                         value="<c:out value="${meal.description}"/>"/> <br/>
    calories : <input type="text" name="calories"
                      value="<c:out value="${meal.calories}"/>"/> <br/>
    time : <input type="datetime" name="time"
                  value="<c:out value="${fn:replace(meal.dateTime, 'T', ' ')}"/>"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
