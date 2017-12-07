<%-- 
    Document   : findFriends
    Created on : Dec 4, 2017, 2:47:46 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Friend</title>
        <link rel='stylesheet' type='text/css' href='../inputFormStyleSheet.css'/>
    </head>
    <body>
        <h1> <div class = "centeredText"> Add a Friend </div> </h1>
        <div class = "centeredText"> 
            <form name="addFriendForm" method="POST" action="../controllerServlet">
            <input type="hidden"  name="action" value="findUserForm">   
            <input type ="text" class = "mediumFont" name="queriedUsername" placeholder="Search for a username...">
            <button type="submit" value="Submit">Search</button>
            <button type ="button" onclick="goBack()">Cancel</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script> 
            </form> 
        </div>
        
        <h1> Search Results </h1> <br> <br>
            <table>
                <tr>
                    <th style="margin-right:10px;">Username</th>
                    <th style="margin-right:10px;">Actions</th>
                </tr>

                <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td>${user.username}</td>
                        <td><a href="<c:url value='/controllerServlet?action=addFriend&username=${user.username}'/>">Send Request</a></td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
