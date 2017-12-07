<%-- 
    Document   : viewGoals
    Created on : Dec 3, 2017, 5:04:02 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Goals</title>
    </head>
    <body>
        
        <h1> Goals for <%= request.getRemoteUser() %> </h1>
        <table>
            <tr>
                <th style="margin-right:10px;">Goal Name</th>
                <th style="margin-right:10px;">Group ID/Name(Optional)</th>
                <th style="margin-right:10px;">Description</th>
                <th style="margin-right:10px;">Start Time</th>
                <th style="margin-right:10px;">Actions</th>
            </tr>

            <c:forEach var="goal" items="${goalsList}">
            <tr>
                <td>${goal.goalName}</td>
                <td>${goal.groupID}</td>
                <td>${goal.goalDescription}</td>
                <td>${goal.startTime}</td>
                <td><a href="<c:url value='/controllerServlet?action=pomodoroSession&goalID=${goal.goalID}'/>">Start Pomodoro!</a></td>
                <td><a href="<c:url value='/controllerServlet?action=viewSingleGoal&goalID=${goal.goalID}'/>">View Details</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
