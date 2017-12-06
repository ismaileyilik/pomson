<%-- 
    Document   : viewFriends
    Created on : Dec 4, 2017, 2:47:54 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Friends</title>
    </head>
    <body>
        Friends of <%= request.getRemoteUser() %> will be listed here
        <table>
            <tr>
                <th style="margin-right:10px;">Username</th>
                <th style="margin-right:10px;">Friends Since</th>
                <th style="margin-right:10px;">Actions</th>
            </tr>

            <c:forEach var="friend" items="${friendsList}">
                <tr>
                    <td>${friendsList.secondFriend}</td>
                    <td>${friendsList.friendsSinceDate}</td>
                    <td><a href="<c:url value='/controllerServlet?action=viewProfile&username=${friendsList.secondFriend}'/>">View Profile</a></td>
                    <td><a href="<c:url value='/controllerServlet?action=removeFriend&username=${friendsList.secondFriend}'/>">Remove Friend</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
