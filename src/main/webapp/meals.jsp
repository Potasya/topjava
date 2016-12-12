<%--
  Created by IntelliJ IDEA.
  User: Marisha
  Date: 11/12/16
  Time: 01:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .green{color: green}
    .red{color: red}
</style>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table border=1 cellpadding=5 cellspacing=0>
    <thead align="center" style="font-weight:bold">
    <tr>
        <th>Id</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Time</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.meals}" var="meal">
        <tr class="${meal.exceed == false ? 'green':'red'}">
            <td><c:out value="${meal.id}" /></td>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
            <td><c:out value="${fn:replace(meal.dateTime, 'T', ' ')}" /></td>
            <td><a href="meals?action=update&id=<c:out value="${meal.id}"/>"> Update </a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>"> Delete </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="meals?action=create">Create new meal</a></p>
</body>
</html>
