<%-- 
    Document   : viewGoals
    Created on : Dec 3, 2017, 4:32:22 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Goal</title>
        <style type="text/css">
            form, table {
                 display:inline;
                 margin:0px;
                 padding:0px;
            }
            
            textarea {
                resize: none; /* user can resize vertically, but width is fixed */
                overflow-y: scroll;
            }
            
        </style>
    </head>
    <body>
        <form name="updateGoalForm" method="POST" action="viewGoalsServerlet">
            Goal Name: <textarea class="scrollabletextbox" rows="2" cols="50" name="goalName"> </textarea> <br>
            Goal Description: <textarea class="scrollabletextbox" rows="6" cols="50" name="goalDescription"> </textarea> <br>
            <button type="submit" value="Submit">Update</button>
            <button type="reset" value="Reset">Reset</button>
        </form>
        
        <button onclick="goBack()">Cancel</button>
        <script>
        function goBack() {
            window.history.back();
        }
        </script> 
        
    </body>
</html>
