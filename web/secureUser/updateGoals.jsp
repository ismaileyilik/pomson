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
        <link rel='stylesheet' type='text/css' href='../inputFormStyleSheet.css'/>
    </head>
    <body>
        <!-- need the following to be stored
            int goalid *This should be done automatically
            int groupid
            string username *This should be done automatically
            string goalname
            string description
            time starttime *This should be done automatically
            time endtime *This should be done later on, automatically
        --> 
        <h1> <div class = "centeredText"> Create a New Goal </div> </h1>
        <div class = "centeredText"> <form name="updateGoalForm" method="POST" action="../updateGoalsServerlet">
            Goal Name: <br>
            <input type ="text" class = "mediumFont" name="goalName"> <br>
            Goal Description: <br>
            <textarea class = "mediumFont" rows="4" cols="60" name="goalDescription"> </textarea> <br>
            Apply Goal to Group[Optional]:
            <select name = "groupIDToApplyTo">
                <option value="None">None</option>
                <option value="Group 1">Group 1</option>
                <option value="Group 2">Group 2</option>
                <option value="Group 3">Group 3</option>
                <option value="Group 4">Group 4</option>
            </select> <br> <br>
            <button type="submit" value="Submit">Update</button>
            <button type="reset" value="Reset">Reset Forms</button>
            <button type ="button" onclick="goBack()">Cancel</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script> 
            
            </form> </div>
    </body>
</html>
