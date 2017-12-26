<%-- 
    Document   : userSettings
    Created on : Dec 6, 2017, 10:24:19 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Settings</title>
    </head>
    <body>
        <div class = "centeredText">
        <h1> Settings </h1>
        <form name="updateUserSettingsForm" method="POST" action="../controllerServlet">
        <input type="hidden"  name="action" value="updateUserSettingsForm">
        Pomodoro Length(Minutes):
        <select name = "pomLength">
            <option value=10>10</option>
            <option value=15>15</option>
            <option value=20>20</option>
            <option value=25>25</option>
            <option value=30>30</option>
            <option value=35>35</option>
            <option value=40>40</option>
            <option value=35>45</option>
            <option value=40>50</option>
        </select><br><br>
        Short Break Length(Minutes):
        <select name = "pomBreakLength">
            <option value=3>3</option>
            <option value=5>5</option>
            <option value=8>8</option>
            <option value=10>10</option>
        </select><br><br>
        Short Break Length(Minutes):
        <select name = "pomLongBreakLength">
            <option value=10>10</option>
            <option value=14>14</option>
            <option value=18>18</option>
            <option value=25>25</option>
        </select><br><br>
        <button type="submit" class ="submit" value="Submit">Save Settings</button>
        </form>
        </div>
    </body>
</html>
