<%-- 
    Document   : createUser
    Created on : Nov 26, 2017, 6:20:02 AM
    Author     : colton
--%>

<%@ page language="Java" import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <form name="signupForm" method="POST" action="signupServerlet">
          Username: <input type="text" name ="Username"> <br>
          Password: <input type="password" name ="Password"> <br>
           <input type = "submit" value="Submit">
        </form>
    </body>
</html>
