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
        <link rel='stylesheet' type='text/css' href='/inputFormStyleSheet.css'/>
    </head>
    <body>
        <div class = "centeredText"> <h1> Create a New Account </h1> </div>
        <div class = "centeredText">
            <form name="signupForm" method="POST" action="controllerServerlet">
              <input type="hidden"  name="action" value="signupForm">
              <input type="text" name ="Username" class = "mediumFont" placeholder="Enter your desired username..."> <br>
              <input type="password" name ="Password" class = "mediumFont" placeholder="Enter your desired password..."> <br>
               <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
