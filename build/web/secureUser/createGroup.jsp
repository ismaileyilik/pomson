<%-- 
    Document   : createGroup
    Created on : Dec 4, 2017, 3:31:43 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a New Group</title>
        <link rel='stylesheet' type='text/css' href='/inputFormStyleSheet.css'/>
    </head>
    <body>
        <h1> <div class = "centeredText"> Create a new group </div> </h1>
        <div class = "centeredText"> <form name="createGroupForm" method="POST" action="../controllerServlet">
            <input type="hidden" name="action" value="createGroupForm">
            Group Name: <br>
            <input type ="text" class = "mediumFont" name="groupName"> <br>
            Description: <br>
            <textarea class = "mediumFont" rows="4" name="groupDescription"> </textarea> <br>
            Verify users as members of the group before allowing them to join?: 
            <input name="verifyUsersCheckBox" type="checkbox"> <br><br>
            <button type="submit" value="Submit">Create Group</button>
            <button type="reset" value="Reset">Reset Forms</button>
            
            <button type = "button" onclick="goBack()">Cancel</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script> 
            
            </form></div>
    </body>
</html>
