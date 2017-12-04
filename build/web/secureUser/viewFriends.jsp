<%-- 
    Document   : viewFriends
    Created on : Dec 4, 2017, 2:47:54 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Friends</title>
    </head>
    <body>
        Friends of <%= request.getRemoteUser() %> will be listed here
    </body>
</html>
