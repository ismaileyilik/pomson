<%-- 
    Document   : findGroups
    Created on : Dec 4, 2017, 2:47:21 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find Groups</title>
        <link rel='stylesheet' type='text/css' href='../inputFormStyleSheet.css'/>
    </head>
    <body>
        <h1> <div class = "centeredText"> Search for a Group </div> </h1>
        <div class = "centeredText"> 
            <form name="findGroupsForm" method="POST" action="../controllerServerlet">
            <input type="hidden"  name="action" value="findGroupsForm">
            <input type ="text" class = "mediumFont" name="groupName" placeholder="Search for a group...">
            <button type="submit" value="Submit">Search</button>
            <button type ="button" onclick="goBack()">Cancel</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script> 
            </form> 
        </div>
        <h1> Found Groups </h1> <br> <br>
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
                    <td><a href="<c:url value='/controllerServerlet?action=joinGroup&groupID=${group.groupID}'/>">Join</a></td>
                </tr>
            </c:forEach>
            </table>
        
        <h1> Pending Group Requests </h1> <br> <br>
    </body>
</html>
