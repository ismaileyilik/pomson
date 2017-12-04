<%-- 
    Document   : login
    Created on : Nov 25, 2017, 9:21:41 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel='stylesheet' type='text/css' href='/inputFormStyleSheet.css'/>
    </head>
    <body>
        <div class = "centeredText"> <h1> Login to Your Account </h1> </div>
        <div class = "centeredText">
        <form action="j_security_check" method="POST">
            <input type="text" name="j_username" class = "mediumFont" placeholder="Username"> <br>
           <input type="password" name="j_password" class = "mediumFont" placeholder="Password"> <br>
           <input type="submit" value="Login">
        </form>
        </div>
    </body>
</html>
