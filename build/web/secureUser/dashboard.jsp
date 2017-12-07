<%-- 
    Document   : dashboard
    Created on : Dec 3, 2017, 4:30:24 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Dashboard</title>
    </head>
    <body>
        <p>Start a Pomodoro <a href="/controllerServlet?action=pomodoroSession" >here</a></p>
        <p>Create a Goal <a href="/controllerServlet?action=updateGoals" >here</a></p>
        <p>Create a Group <a href="/controllerServlet?action=createGroup" >here</a></p>
        <p>View Goals <a href="/controllerServlet?action=viewGoals" >here</a></p>
        <p>View Groups <a href="/controllerServlet?action=viewGroups" >here</a></p>
        <p>View Friends <a href="/controllerServlet?action=viewFriends" >here</a></p>
        <p>Add Friends <a href="/controllerServlet?action=findFriends" >here</a></p>
        <p>Find Groups <a href="/controllerServlet?action=findGroups" >here</a></p>
        <%-- <p>Edit Settings <a href="/controllerServlet?action=userSettings" >here</a></p>--%>


    </body>
</html>

