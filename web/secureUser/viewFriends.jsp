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

            <c:forEach var="friendship" items="${friendsList}">
                <tr>
                    <td>${friendship.secondFriend}</td>
                    <td>${friendship.friendsSinceDate}</td>
                    <td><a href="<c:url value='/controllerServlet?action=viewProfile&username=${friendship.secondFriend}'/>">View Profile</a></td>
                    <td><a href="<c:url value='/controllerServlet?action=removeFriend&username=${friendship.secondFriend}'/>">Remove Friend</a></td>
                </tr>
            </c:forEach>
        </table>
        <h1> Incoming Friend Requests </h1> <br> <br>
        <table>
            <c:forEach var="incoming" items="${incomingList}">
                <tr>
                    <td>${incoming.requestor}</td>
                    <td><a href="<c:url value='/controllerServlet?action=acceptFriendRequest&username=${incoming.requestor}'/>">Accept Request</a></td>
                    <td><a href="<c:url value='/controllerServlet?action=denyIncomingFriendRequest&username=${incoming.requestor}'/>">Deny Request</a></td>
                </tr>
            </c:forEach>
        </table>
        <h1> Outgoing Friend Requests </h1> <br> <br>
        <table>
            <c:forEach var="outgoing" items="${outgoingList}">
                <tr>
                    <td>${outgoing.requestee}</td>
                    <td><a href="<c:url value='/controllerServlet?action=denyOutgoingFriendRequest&username=${outgoing.requestee}'/>">Cancel Request</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
