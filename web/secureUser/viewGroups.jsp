<%-- 
    Document   : viewGroups
    Created on : Dec 4, 2017, 2:47:31 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Groups</title>
    </head>
    <body>
        <h1> Groups that <%= request.getRemoteUser() %> is a member of </h1>
        <table>
            <tr>
                <th style="margin-right:5px;">Group Name</th>
                <th style="margin-right:5px;">Description</th>
                <th style="margin-right:5px;">Verify before Joining</th>
            </tr>

            <c:forEach var="group" items="${groupList}">
                <tr>
                    <td>${group.groupName}</td>
                    <td>${group.description}</td>
                    <td>${group.verifyBeforeJoining}</td>
                    <td><a href="<c:url value='/controllerServlet?action=viewGroup&groupID=${group.groupID}'/>">View</a></td>
                    <td><a href="<c:url value='/controllerServlet?action=leaveGroup&groupID=${group.groupID}'/>">Leave</a></td>
                </tr>
            </c:forEach>
            </table>
    </body>
</html>
