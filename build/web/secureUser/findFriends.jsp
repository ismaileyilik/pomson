<%-- 
    Document   : findFriends
    Created on : Dec 4, 2017, 2:47:46 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form name="addFriendForm" method="POST" action="../controllerServerlet">
            <input type="hidden"  name="action" value="addFriendForm">   
            <input type ="text" class = "mediumFont" name="friendsUsername" placeholder="Search for a username...">
            <button type="submit" value="Submit">Send Friend Request</button>
            <button type ="button" onclick="goBack()">Cancel</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script> 
            </form> 
        </div>
        <h1> Pending Incoming Friend Requests </h1> <br> <br>
        <h1> Pending Outgoing Friend Requests </h1> <br> <br>
    </body>
</html>
