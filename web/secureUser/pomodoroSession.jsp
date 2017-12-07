<%-- 
    Document   : pomodoroSession
    Created on : Dec 4, 2017, 7:27:12 PM
    Author     : colton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel='stylesheet' type='text/css' href='../inputFormStyleSheet.css'/>
        <title>Start a Pomodoro!</title>
    </head>
    <body>
<!-- Display the timer in an element -->
        <h1> <div class = "centeredText"><p id="timer"></p></div> </h1>
        <br>
        <div class = "centeredText"> 
        <form name="savePomodoroForm" method="POST" action="../controllerServlet">
        <input type="hidden" name="action" value="savePomodoroForm">
            Task: <br>
            <input type ="text" class = "mediumFont" name="task"> <br>
            <input type=hidden id="pomLength" name="pomLength" value="${userPrefs.pomodoroLengthPreferenceMins}"/>
            Comments: <br>
            <textarea class = "mediumFont" rows="4" cols="60" name="comments"> </textarea> <br>
            Apply Task to Goal:
            <select name = "goalIDToApplyTo">
                <option value=0>None</option>
                <c:forEach var="goal" items="${goalsList}">
                    <option value="${goal.goalID}"/>${goal.goalName}</option>
                </c:forEach>
            </select> <br> <br>
            <button class="start">Start</button>
            <button class="pause">Pause</button>
            <button class="reset">Reset</button>
            <button type="button" onclick="goBack()">Cancel</button>
            <button type="submit" class ="saveButton" value="Submit">Save Pomodoro</button>
        </form>
        </div>
        
        <script>
            var isPaused = true;
            var defaultPomodoroLength = calcMinutesInSeconds(document.getElementById('pomLength').value);
            var totalSecondsLeft = defaultPomodoroLength;
            var minutesLeft = Math.floor(totalSecondsLeft/60);
            var secondsLeft = totalSecondsLeft % 60;
            // Update the count down every 1 second
            var x = setInterval(updateTimer, 1000);
            document.getElementById("timer").innerHTML = "Time Left: [" + minutesLeft + " minutes, " + secondsLeft + " seconds]";


            //Use jquery for the buttons actions
            $(document).ready(function(){
                $(".pause").hide();
                $(".saveButton").hide();
            });

            $('.pause').on('click', function(e) {
                e.preventDefault();
                isPaused = true;
                $(".start").show();
                $(".pause").hide();
            });

            $('.start').on('click', function(e) {
                e.preventDefault();
                isPaused = false;
                $(".start").hide();
                $(".pause").show();

            });

            $('.reset').on('click', function(e) {
                e.preventDefault();
                isPaused = true;
                totalSecondsLeft = defaultPomodoroLength;
                minutesLeft = Math.floor(totalSecondsLeft/60);
                secondsLeft = totalSecondsLeft % 60;
                document.getElementById("timer").innerHTML = "Time Left: [" + minutesLeft + " minutes, " + secondsLeft + " seconds]";
                $(".start").show();
                $(".pause").hide();
                $(".saveButton").hide();
            });

            function goBack() {
                window.history.back();
            }

            function calcMinutesInSeconds(minutesDesired){
                var secondsNeeded = 60 * minutesDesired;
                return secondsNeeded;
            }

            //Function to handle updating the timer
            function updateTimer(){
                if(!isPaused && totalSecondsLeft > 0){
                    totalSecondsLeft -= 1;
                    minutesLeft = Math.floor(totalSecondsLeft/60);
                    secondsLeft = totalSecondsLeft % 60;
                    // Display the result in the element with id="demo"
                    document.getElementById("timer").innerHTML = "Time Left: [" + minutesLeft + " minutes, " + secondsLeft + " seconds]";
                    // If the count down is finished, write some text
                    if (secondsLeft <= 0) {
                      $(".saveButton").show();
                      clearInterval(x);
                    }
                }
            }
        </script>
    </body>
</html>
