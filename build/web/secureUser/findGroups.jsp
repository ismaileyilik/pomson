<%-- 
    Document   : findGroups
    Created on : Dec 4, 2017, 2:47:21 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find Groups</title>
        <link rel='stylesheet' type='text/css' href='../inputFormStyleSheet.css'/>
    </head>
    <body>
        <h1> <div class = "centeredText"> Search for a Group </div> </h1>
        <div class = "centeredText"> 
            <form name="findGroupsForm" method="POST" action="../controllerServerlet">
            <input type="hidden"  name="action" value="findGroupsForm">
            <input type ="text" class = "mediumFont" name="groupName" placeholder="Search for a group...">
            <button type="submit" value="Submit">Search</button>
            <button type ="button" onclick="goBack()">Cancel</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script> 
            </form> 
        </div>
        <h1> Pending Group Requests </h1> <br> <br>
    </body>
</html>
