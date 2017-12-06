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
        <p>Start a Pomodoro <a href="/controllerServerlet?action=pomodoroSession" >here</a></p>
        <p>View Goals <a href="/controllerServerlet?action=viewGoals" >here</a></p>
        <p>Edit Goals <a href="/controllerServerlet?action=updateGoals" >here</a></p>
        <p>Create a Group <a href="/controllerServerlet?action=createGroup" >here</a></p>
        <p>Find Groups <a href="/controllerServerlet?action=findGroups" >here</a></p>
        <p>View Groups <a href="/controllerServerlet?action=viewGroups" >here</a></p>
        <p>Add Friends <a href="/controllerServerlet?action=findFriends" >here</a></p>
        <p>View Friends <a href="/controllerServerlet?action=viewFriends" >here</a></p>

    </body>
</html>

