===================================================================================================================IMPORTANT, EXECUTION INSTRUCTIONS=========================================================================================
In order to start the program, you must configure the sql database to be named PomodoroDatabase, running on port 3306 as set by default when using mySqlWorkbench. The credentials for logging into the database should be:
"root", "wordpass". Should you need to change these variables, they must be configured in the context.xml portion of the project. The credentials for connecting to the mysql server will also have to be changed in the DatabaseDriver.java class, if you wish to use different credentials. Once these two spots are changed, the only thing left to do is execute the "PomodoroDatabaseInitialization.sql" script that is included in the project folder. Then, your database should be initialized with the correct tables, the credentials in the the code should be able to connect, and you will only have to run the server files.


========================================================================================================================ABOUT THE PROJECT=====================================================================================================
This project is based off of the Pomodoro Technique, and encompasses software for helping multiple people utilize the technique and share data with each other. 

According to wikipedia, "The Pomodoro Technique is a time management method developed by Francesco Cirillo in the late 1980s.[1] The technique uses a timer to break down work into intervals, traditionally 25 minutes in length, separated by short breaks. These intervals are named pomodoros, the plural in English of the Italian word pomodoro (tomato), after the tomato-shaped kitchen timer that Cirillo used as a university student.[2][3]"

There are six steps in the original technique:
    1. Decide on the task to be done.
    2. Set the pomodoro timer (traditionally to 25 minutes).[1]
    3. Work on the task.
    4. End work when the timer rings and put a checkmark on a piece of paper.
    5. If you have fewer than four checkmarks, take a short break (3–5 minutes), then go to step 2.
    6. After four pomodoros, take a longer break (15–30 minutes), reset your checkmark count to zero, then go to step 1.

This software expands on the concept further by allowing users to track their goals, and apply "Pomodoros"(sessions of work) towards their goals. This way, users will be able to tell every step they took towards completing their goals, as well as what their friends are doing!
========================================================================================================================TECH STACK=============================================================================================================
The stack I used is composed of Tomcat, JDBC, and SQL. 
