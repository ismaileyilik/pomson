<%-- 
    Document   : viewGoals
    Created on : Dec 3, 2017, 4:32:22 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Goal</title>
        <link rel='stylesheet' type='text/css' href='../inputFormStyleSheet.css'/>
    </head>
    <body>
        <h1> <div class = "centeredText"> Create a New Goal </div> </h1>
        <div class = "centeredText"> 
        <form name="updateGoalForm" method="POST" action="../controllerServlet">
            <input type="hidden"  name="action" value="updateGoalForm">
            Goal Name: <br>
            <input type ="text" class = "mediumFont" name="goalName"> <br>
            Goal Description: <br>
            <textarea class = "mediumFont" rows="4" cols="60" name="goalDescription"> </textarea> <br>
            Apply Goal to Group[Optional]:
            <select name = "groupIDToApplyTo">
                <option value=0>None</option>
                <option value=1>None 2</option>
                <c:forEach var="group" items="${membershipGroupIDList}">
                    <option value="${group.groupID}"/>${group.groupName}</option>
                </c:forEach>
            </select> <br> <br>
            <button type="submit" value="Submit">Update</button>
            <button type="reset" value="Reset">Reset Forms</button>
            <button type ="button" onclick="goBack()">Cancel</button>
        </form>
        </div>
        <script>
            function goBack() {
                window.history.back();
            }
        </script> 
    </body>
</html>
